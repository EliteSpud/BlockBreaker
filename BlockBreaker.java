import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import java.awt.Color;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class BlockBreaker extends Application
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
		
		Rectangle scoreRect = new Rectangle(100,63,Paint.valueOf("0000FF"));
		scoreRect.relocate(404,0);
		
		Rectangle testRect = new Rectangle(49,49,Paint.valueOf("00FF00"));
		testRect.relocate(150,150);
		
		Rectangle fireRect = new Rectangle(100,190,Paint.valueOf("FF0000"));
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
		
		StackPane stackFire = new StackPane();
		stackFire.getChildren().addAll(fireRect,fireText);
		stackFire.setLayoutX(404);
		stackFire.setLayoutY(706);
		
		rightPane.getChildren().addAll(scoreRect,stackFire);
		
		root.getChildren().addAll(gamePane,rightPane,testRect);
		scene.getStylesheets().add(BlockBreaker.class.getResource("gameStyle.css").toExternalForm());
		mainStage.setScene(scene); 
		mainStage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}