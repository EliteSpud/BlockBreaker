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
import java.util.ArrayList;

public class Main extends Application
{
	int currentRound = 0;
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
	}
}