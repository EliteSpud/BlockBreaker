import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
public class SetValues
{
	public void set(ArrayList blockValues,Text gameText[],GenerateNumbers gn,BuildGUI bg)
	{
		int currentRows = gn.getCurrentRows();
		for(int i = (currentRows*8)-1; i > -1; i--)
		{
			gameText[i].setText(String.valueOf(blockValues.get(i)));
			Rectangle gameRect[] = bg.getGameRect();
			
			if(gameText[i].getText() != "") //setting blocks to be different shades of blue depending on the size of their value
			{
				if(Integer.parseInt(gameText[i].getText()) < 10) //0 to 9
				{
					gameRect[i].setFill(Paint.valueOf("3333FF"));
				}
				else if(Integer.parseInt(gameText[i].getText()) < 20 && Integer.parseInt(gameText[i].getText()) > 9) //10 to 19
				{
					gameRect[i].setFill(Paint.valueOf("0000FF"));
				}
				else if(Integer.parseInt(gameText[i].getText()) < 30 && Integer.parseInt(gameText[i].getText()) > 19) //20 to 29
				{
					gameRect[i].setFill(Paint.valueOf("0000CC"));
				}
				else if(Integer.parseInt(gameText[i].getText()) < 40 && Integer.parseInt(gameText[i].getText()) > 29) //30 to 39
				{
					gameRect[i].setFill(Paint.valueOf("000099"));
				}
				else if(Integer.parseInt(gameText[i].getText()) < 51 && Integer.parseInt(gameText[i].getText()) > 39) //40 to 50
				{
					gameRect[i].setFill(Paint.valueOf("000066"));
				}
			}
		}
	}
}