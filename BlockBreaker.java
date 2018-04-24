import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class BlockBreaker extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage mainStage)
	{
		mainStage.setTitle("Block Breaker");
		
		
		
		StackPane rootPane = new StackPane();
		mainStage.setScene(new Scene(rootPane,504,896)); //9:16 aspect ratio, divisible by 8
		mainStage.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}