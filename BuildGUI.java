import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Paint;
import java.awt.Color;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.geometry.Bounds;

import javafx.animation.Timeline;
import javafx.animation.AnimationTimer;

import javafx.concurrent.Task;

public class BuildGUI
{
	Pane root;
	Scene scene;
	Pane rightPane;
	Pane leftPane;
	Rectangle menuRect;
	Text menuText;
	Rectangle scoreRect;
	Text scoreText;
	Rectangle scoreMeterRect;
	Rectangle fireRect;
	Text fireText;
	ArrayList<Circle> balls;
	Line aimLine = new Line();
	StackPane stackMenu;
	StackPane stackScore;
	StackPane stackStars;
	StackPane stackFire;
	//main game area elements
	GridPane gridGame;
	StackPane stackGrid[];
	Rectangle gameRect[];
	Text gameText[];
	Pane bottomPane;
	//testing elements
	Button btnNextRound;
	
	int maxBlocks = 112; //maximum number of blocks on screen
	int ballCenterX = 196;
	int ballCenterY = 680;
	boolean aimed = false;
	
	
	double xSpeed[]; //controls how fast the balls move horizontally
	double ySpeed[]; //controls how fast the balls move vertically
	
	public void build(Stage s, GenerateNumbers gn,BuildGUI bg,ArrayList blockValues,Aim aim,Fire fire)
	{
		s.setTitle("Block Breaker");

		root = new Pane(); //root of the scene
		scene = new Scene(root,504,896); //9:16 aspect ratio, divisible by 8
		rightPane = new Pane();
		BlockGaps gap = new BlockGaps();
		int initialRows = gn.getInitialRows();
		Round round = new Round();
		
		leftPane = new Pane();
		leftPane.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				aim.drawLine(event.getX(),event.getY(),bg);
			}
		});
		
		bottomPane = new Pane();
		balls = new ArrayList<Circle>();
		makeBalls();
		
		menuRect = new Rectangle(112,63,Paint.valueOf("0000FF"));
		menuRect.setStrokeType(StrokeType.INSIDE);
		menuRect.setStroke(Paint.valueOf("000000"));
		menuText = new Text("Menu");
		
		scoreRect = new Rectangle(112,63,Paint.valueOf("0000FF"));
		scoreRect.setStrokeType(StrokeType.INSIDE);
		scoreRect.setStroke(Paint.valueOf("000000"));
		scoreText = new Text("scoretest");
		
		scoreMeterRect = new Rectangle(112,560,Paint.valueOf("9BA288"));
		scoreMeterRect.setStrokeType(StrokeType.INSIDE);
		scoreMeterRect.setStroke(Paint.valueOf("000000"));
		
		fireRect = new Rectangle(112,210,Paint.valueOf("FF0000"));
		fireRect.setStrokeType(StrokeType.INSIDE);
		fireRect.setStroke(Paint.valueOf("000000"));
		fireRect.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				System.out.println("//////////////////////////FIRE CLICKED//////////////////////////");
				fire.fire(bg,gn);
			}
		});	
		fireText = new Text("FIRE");
		fireText.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				System.out.println("//////////////////////////FIRE CLICKED//////////////////////////");
				fire.fire(bg,gn);
			}
		});
		
		
		stackMenu = new StackPane();
		stackMenu.getChildren().addAll(menuRect,menuText);
		stackMenu.setLayoutX(392);
		stackMenu.setLayoutY(0);
		
		stackScore = new StackPane();
		stackScore.getChildren().addAll(scoreRect,scoreText);
		stackScore.setLayoutX(392);
		stackScore.setLayoutY(63);
		
		stackStars = new StackPane();
		stackStars.getChildren().addAll(scoreMeterRect);
		stackStars.setLayoutX(392);
		stackStars.setLayoutY(126);
		
		stackFire = new StackPane();
		stackFire.getChildren().addAll(fireRect,fireText);
		stackFire.setLayoutX(392);
		stackFire.setLayoutY(686);
		
		//////////game area starts here//////////
		
		gridGame = new GridPane();
		stackGrid = new StackPane[maxBlocks];
		gameRect = new Rectangle[maxBlocks];
		gameText = new Text[maxBlocks];
		
		int totalRows = gn.getTotalRows();
		gap.gaps(totalRows*8); //generates boolean array of true/false to determine if there is a gap (true) or not (false).
		placeBlocks(gn,bg,gap,blockValues,true);
		
		
		gridGame.setLayoutX(0);
		gridGame.setLayoutY(0);
		
		//game area ends here//
		
		btnNextRound = new Button("Next Round");
		btnNextRound.setLayoutX(50);
		btnNextRound.setLayoutY(720);
		btnNextRound.getStyleClass().add("btnNextRound");
		btnNextRound.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				nextRound(gn,initialRows,bg,gap,blockValues,round);
			}
		});
		
		leftPane.setPrefWidth(392);
		leftPane.getChildren().addAll(bottomPane,btnNextRound);
		rightPane.getChildren().addAll(stackMenu,stackScore,stackStars,stackFire);
		root.getChildren().addAll(gridGame,rightPane,leftPane);
		scene.getStylesheets().add(Main.class.getResource("gameStyle.css").toExternalForm());
		
		s.setScene(scene); 
		s.show();
	}
	/*public void bounce(String side,int pos)
	{
		if(side.equals("right") || side.equals("left"))
		{
			xSpeed[pos] = -xSpeed[pos];
		}
		if(side.equals("top"))
		{
			ySpeed[pos] = -ySpeed[pos];
		}
	}
	public void bottom(int ballNum)
	{
		System.out.println("Bottom : "+ballNum);
		//set speed of this ball to 0
		xSpeed[ballNum] = 0;
		ySpeed[ballNum] = 0;
		//set centre to original centre
		balls.get(ballNum).setCenterX(ballCenterX);
		balls.get(ballNum).setCenterY(ballCenterY);
	}*/
	public void nextRound(GenerateNumbers gn,int initialRows,BuildGUI bg,BlockGaps gap,ArrayList blockValues,Round round)
	{
		int roundNum = round.next();
		System.out.println("Round : "+roundNum);
		removeBlocks(gn);
		shiftBlocks(gn,initialRows,roundNum,bg,gap,blockValues);
		
		//root.requestLayout();
	}
	public void removeBlocks(GenerateNumbers gn)
	{
		int currentRows = gn.getCurrentRows();
		for(int i = 0; i < currentRows*8; i++)
		{
			stackGrid[i].getChildren().clear();
			gridGame.getChildren().remove(stackGrid[i]);
		}
	}
	public void placeBlocks(GenerateNumbers gn,BuildGUI bg,BlockGaps gap,ArrayList blockValues,boolean moreRowsStored)
	{
		SetValues sv = new SetValues();
		int currentRows = gn.getCurrentRows();
		if(moreRowsStored == true)
		{
			for(int i = (currentRows*8)-1; i > -1; i--)
			{
				stackGrid[i] = new StackPane();
				gameRect[i] = new Rectangle(49,49,Paint.valueOf("FFFFFF"));
				gameRect[i].setStrokeType(StrokeType.INSIDE);
				gameRect[i].setStroke(Paint.valueOf("000000"));
				gameText[i] = new Text();
				gameText[i].setFill(Paint.valueOf("FFFFFF"));
				if(i < 8*currentRows)
				{
					stackGrid[i].getChildren().addAll(gameRect[i],gameText[i]);
					stackGrid[i].setId("intact");
				}
			}
			sv.set(blockValues,gameText,gn,bg);
			
			ArrayList<Boolean> gaps = gap.getBlockGaps();
			
			int count = (currentRows*8)-1;
			for(int col = 0; col < currentRows; col++)
			{
				for(int row = 0; row < 8; row++)
				{
					if(gaps.get(count) == false)
					{
						gridGame.add(stackGrid[count],row,col);
					}
					count--;
				}
			}
		}
		else if(moreRowsStored == false)
		{
			for(int i = (currentRows*8)-1; i > -1; i--)
			{
				stackGrid[i] = new StackPane();
				gameRect[i] = new Rectangle(49,49,Paint.valueOf("000000"));
				gameRect[i].setStrokeType(StrokeType.INSIDE);
				gameRect[i].setStroke(Paint.valueOf("000000"));
				gameText[i] = new Text();
				gameText[i].setFill(Paint.valueOf("FFFFFF"));
				if(i < 8*(currentRows))
				{
					stackGrid[i].getChildren().addAll(gameRect[i],gameText[i]);
					stackGrid[i].setId("intact");
				}
				sv.set(blockValues,gameText,gn,bg);
			
				ArrayList<Boolean> gaps = gap.getBlockGaps();
				
				int count = (currentRows*8)-1;
				int totalRows = gn.getTotalRows();
				for(int col = 0; col < currentRows; col++)
				{
					for(int row = 0; row < 8; row++)
					{
						if(((currentRows - totalRows)*8) - 1 > count)
						{
							gridGame.add(stackGrid[count],row,col);
						}
						else if(currentRows > totalRows)
						{
							int offset = (currentRows - totalRows)*8;
							if(gaps.get(count-offset) == false)
							{
								gridGame.add(stackGrid[count],row,col);
							}
						}
						else if(gaps.get(count) == false)
						{
							gridGame.add(stackGrid[count],row,col);
						}
						count--;
					}
				}
			}
		}
	}
	public void shiftBlocks(GenerateNumbers gn,int initialRows,int roundNum,BuildGUI bg,BlockGaps gap, ArrayList blockValues)
	{
		int currentRows = initialRows + roundNum;
		gn.setCurrentRows(currentRows);
		System.out.println("currentRows = "+currentRows);
		if(currentRows <= gn.getTotalRows())
		{	
			placeBlocks(gn,bg,gap,blockValues,true);
		}
		else
		{
			placeBlocks(gn,bg,gap,blockValues,false);
		}
	}
	public void makeBalls()
	{
		int startBalls = 5;
		for(int i = 0;i < startBalls; i++)
		{
			balls.add(new Circle());
			balls.get(i).setRadius(5);
			balls.get(i).setCenterX(196);
			balls.get(i).setCenterY(680);
			balls.get(i).setFill(Paint.valueOf("DDDDDD"));
		}
		bottomPane.getChildren().addAll(balls);
	}
	public Rectangle[] getGameRect()
	{
		return gameRect;
	}
	public Text[] getGameText()
	{
		return gameText;
	}
	public StackPane[] getStackGrid()
	{
		return stackGrid;
	}
	public GridPane getGridGame()
	{
		return gridGame;
	}
	public Bounds getGridGameBounds()
	{
		return gridGame.getBoundsInParent();
	}
	public Line getLine()
	{
		return aimLine;
	}
	public int getBallCenterX()
	{
		return ballCenterX;
	}
	public int getBallCenterY()
	{
		return ballCenterY;
	}
	public Pane getLeftPane()
	{
		return leftPane;
	}
	public boolean getAimed()
	{
		return aimed;
	}
	public void setAimed(boolean aimedState)
	{
		aimed = aimedState;
	}
	public ArrayList<Circle> getBalls()
	{
		return balls;
	}
	public Scene getScene()
	{
		return scene;
	}
	public Pane getRoot()
	{
		return root;
	}
}