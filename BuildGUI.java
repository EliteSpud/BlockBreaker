import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Paint;
import java.awt.Color;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;


public class BuildGUI
{
	Pane root;
	Scene scene;
	Pane rightPane;
	Rectangle menuRect;
	Text menuText;
	Rectangle scoreRect;
	Text scoreText;
	Rectangle scoreMeterRect;
	Rectangle fireRect;
	Text fireText;
	StackPane stackMenu;
	StackPane stackScore;
	StackPane stackStars;
	StackPane stackFire;
	//main game area elements
	GridPane gridGame;
	StackPane stackGrid[];
	Rectangle gameRect[];
	Text gameText[];
	//testing elements
	Button btnNextRound;
	
	int maxBlocks = 112; //maximum number of blocks on screen
	
	public void build(Stage s, GenerateNumbers gn,BuildGUI bg,ArrayList blockValues)
	{
		s.setTitle("Block Breaker");
		
		root = new Pane(); //root of the scene
		scene = new Scene(root,504,896); //9:16 aspect ratio, divisible by 8
		rightPane = new Pane();
		
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
				System.out.println("FIRE CLICKED");
				//fire
			}
		});
		fireText = new Text("FIRE");
		fireText.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				System.out.println("FIRE CLICKED");
				//fire
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
		
		rightPane.getChildren().addAll(stackMenu,stackScore,stackStars,stackFire);
		
		//////////game area starts here//////////
		
		gridGame = new GridPane();
		stackGrid = new StackPane[maxBlocks];
		gameRect = new Rectangle[maxBlocks];
		gameText = new Text[maxBlocks];
		
		BlockGaps gap = new BlockGaps();
		int totalRows = gn.getTotalRows();
		gap.gaps(totalRows*8); //generates boolean array of true/false to determine if there is a gap (true) or not (false).
		placeBlocks(gn,bg,gap,blockValues);
		
		gridGame.setLayoutX(0);
		gridGame.setLayoutY(0);
		
		//game area ends here//
		
		Round round = new Round();
		
		int initialRows = gn.getInitialRows();
		btnNextRound = new Button("Next Round");
		btnNextRound.setLayoutX(50);
		btnNextRound.setLayoutY(720);
		btnNextRound.getStyleClass().add("btnNextRound");
		btnNextRound.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				int roundNum = round.next();
				System.out.println("Round : "+roundNum);
				removeBlocks(gn);
				shiftBlocks(gn,initialRows,roundNum,bg,gap,blockValues);
			}
		});
		
		
		root.getChildren().addAll(rightPane,gridGame,btnNextRound);
		scene.getStylesheets().add(Main.class.getResource("gameStyle.css").toExternalForm());
		
		s.setScene(scene); 
		s.show();
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
	public void placeBlocks(GenerateNumbers gn,BuildGUI bg,BlockGaps gap,ArrayList blockValues)
	{
		SetValues sv = new SetValues();
		int currentRows = gn.getCurrentRows();
		for(int i = (currentRows*8)-1; i > -1; i--)
		{
			stackGrid[i] = new StackPane();
			gameRect[i] = new Rectangle(49,49,Paint.valueOf("FFFFFF"));
			gameRect[i].setStrokeType(StrokeType.INSIDE);
			gameRect[i].setStroke(Paint.valueOf("000000"));
			gameText[i] = new Text();
			if(i < 8*currentRows)
			{
				stackGrid[i].getChildren().addAll(gameRect[i],gameText[i]);
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
	public void shiftBlocks(GenerateNumbers gn,int initialRows,int roundNum,BuildGUI bg,BlockGaps gap, ArrayList blockValues)
	{
		int currentRows = initialRows + roundNum;
		gn.setCurrentRows(currentRows);
		System.out.println("currentRows = "+currentRows);
		
		placeBlocks(gn,bg,gap,blockValues);
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
	public GridPane getgridGame()
	{
		return gridGame;
	}
}