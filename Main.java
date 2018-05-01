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
<<<<<<< HEAD
import javafx.scene.control.Button;

public class Main extends Application
{
	Pane root;
	Scene scene;
	Pane gamePane;
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
	FlowPane flowGame;
	StackPane stackGrid[];
	Rectangle gameRect[];
	Text gameText[];
	Button btnNextRound;
	
=======
import java.util.ArrayList;

public class Main extends Application
{
	int currentRound = 0;
>>>>>>> parent of 3fc22d7... Revert "Commit 11"
	public static void main(String[] args)
	{
		launch(args);
	}
	public void start(Stage mainStage)
	{
		BuildGUI bg = new BuildGUI();
		GenerateNumbers gn = new GenerateNumbers();
		ArrayList<Integer> blockValues = new ArrayList<Integer>(gn.generate(1,51,20));
		//blockValues = gn.generate(1,51,20); //params : level, high, low
		bg.build(mainStage,gn,bg,blockValues);
		
		buildGame(bg,gn,blockValues);
		//generate colours based on numbers
		//spaces in-between blocks
		//make them move down each round (have a button to test it)
		//make balls + counter
		//add direction choice
		//bouncing
		//decrement block value
		//win/lose function
		//lasers
		//score counter
		//score meter
	}
	public void buildGame(BuildGUI bg,GenerateNumbers gn,ArrayList blockValues)
	{
		
		Text gameText[] = bg.getGameText();
		int startPos = blockValues.size()-1;
		
		SetValues sv = new SetValues();
		sv.set(blockValues,gameText,gn,bg);
		int currentRows = gn.getCurrentRows();
		for(int i = (currentRows*8)-1; i > -1; i--)
		{
			FlowPane flowGame = bg.getFlowGame();
			StackPane stackGrid[] = bg.getStackGrid();
			//flowGame.getChildren().add(stackGrid[i]);
		}
		
		/*for(int i = 0; i < blockValues.size(); i++)
		{
			gameText[startPos-i].setText(String.valueOf(blockValues.get(i)));
			Rectangle gameRect[] = bg.getGameRect();
			if(gameText[startPos-i].getText() != "")
			{
				if(Integer.parseInt(gameText[startPos-i].getText()) >= 30)
				{
					gameRect[startPos-i].setFill(Paint.valueOf("FFFF00"));
				}
			}
<<<<<<< HEAD
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
		
		btnNextRound
		
		flowGame = new FlowPane();
		stackGrid = new StackPane[112]; 
		gameRect = new Rectangle[112];
		gameText = new Text[112];
		for(int i = 0; i < stackGrid.length; i++)
		{
			stackGrid[i] = new StackPane();
			gameRect[i] = new Rectangle(49,49,Paint.valueOf("FFFFFF"));
			gameRect[i].setStrokeType(StrokeType.INSIDE);
			gameRect[i].setStroke(Paint.valueOf("000000"));
			gameText[i] = new Text("0");
			stackGrid[i].getChildren().addAll(gameRect[i],gameText[i]);
			flowGame.getChildren().add(stackGrid[i]);
		}
		flowGame.setLayoutX(0);
		flowGame.setLayoutY(0);
		
		root.getChildren().addAll(gamePane,rightPane,flowGame);
		scene.getStylesheets().add(Main.class.getResource("gameStyle.css").toExternalForm());
		
		mainStage.setScene(scene); 
		mainStage.show();
	}
	public void run()
	{
		BuildGUI bg = new BuildGUI();
		System.out.println("test done");
	}
	public Rectangle getGameRect()
	{
		return gameRect;
	}
	public Text getGameText()
	{
		return gameText;
	}
	public StackPane getStackGrid()
	{
		return stackGrid;
=======
			FlowPane flowGame = bg.getFlowGame();
			StackPane stackGrid[] = bg.getStackGrid();
			flowGame.getChildren().add(stackGrid[i]);
		}*/
	}
	public int getCurrentRound()
	{
		return currentRound;
	}
	public void setCurrentRound(int round)
	{
		currentRound = round;
>>>>>>> parent of 3fc22d7... Revert "Commit 11"
	}
}