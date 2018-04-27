import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Paint;
import java.awt.Color;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage mainStage)
	{
		mainStage.setTitle("Block Breaker");
		
		
		
		Pane root = new Pane(); //root of the scene
		Scene scene = new Scene(root,504,896); //9:16 aspect ratio, divisible by 8
		Pane gamePane = new Pane();
		Pane rightPane = new Pane();
		
		Rectangle menuRect = new Rectangle(100,63,Paint.valueOf("0000FF"));
		menuRect.setStrokeType(StrokeType.INSIDE);
		menuRect.setStroke(Paint.valueOf("000000"));
		Text menuText = new Text("Menu");
		
		Rectangle scoreRect = new Rectangle(100,63,Paint.valueOf("0000FF"));
		scoreRect.setStrokeType(StrokeType.INSIDE);
		scoreRect.setStroke(Paint.valueOf("000000"));
		Text scoreText = new Text("scoretest");
		
		Rectangle scoreMeterRect = new Rectangle(100,580,Paint.valueOf("9BA288"));
		scoreMeterRect.setStrokeType(StrokeType.INSIDE);
		scoreMeterRect.setStroke(Paint.valueOf("000000"));
		
		Rectangle fireRect = new Rectangle(100,190,Paint.valueOf("FF0000"));
		fireRect.setStrokeType(StrokeType.INSIDE);
		fireRect.setStroke(Paint.valueOf("000000"));
		fireRect.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				System.out.println("FIRE CLICKED");
				//fire
			}
		});
		Text fireText = new Text("FIRE");
		fireText.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event)
			{
				System.out.println("FIRE CLICKED");
				//fire
			}
		});
		
		StackPane stackMenu = new StackPane();
		stackMenu.getChildren().addAll(menuRect,menuText);
		stackMenu.setLayoutX(404);
		stackMenu.setLayoutY(0);
		
		StackPane stackScore = new StackPane();
		stackScore.getChildren().addAll(scoreRect,scoreText);
		stackScore.setLayoutX(404);
		stackScore.setLayoutY(63);
		
		StackPane stackStars = new StackPane();
		stackStars.getChildren().addAll(scoreMeterRect);
		stackStars.setLayoutX(404);
		stackStars.setLayoutY(126);
		
		StackPane stackFire = new StackPane();
		stackFire.getChildren().addAll(fireRect,fireText);
		stackFire.setLayoutX(404);
		stackFire.setLayoutY(706);
		
		rightPane.getChildren().addAll(stackMenu,stackScore,stackStars,stackFire);
		
		root.getChildren().addAll(gamePane,rightPane);
		scene.getStylesheets().add(BlockBreaker.class.getResource("gameStyle.css").toExternalForm());
		mainStage.setScene(scene); 
		mainStage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}