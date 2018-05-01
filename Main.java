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
	
	public static void main(String[] args)
	{
		launch(args);
	}
	public void start(Stage mainStage)
	{
		mainStage.setTitle("Block Breaker");
		
		root = new Pane(); //root of the scene
		scene = new Scene(root,504,896); //9:16 aspect ratio, divisible by 8
		gamePane = new Pane();
		rightPane = new Pane();
		
		menuRect = new Rectangle(112,63/*,Paint.valueOf("0000FF")*/);
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
	}
}