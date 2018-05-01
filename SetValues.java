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
			if(gameText[i].getText() != "")
			{
				if(Integer.parseInt(gameText[i].getText()) >= 30)
				{
					gameRect[i].setFill(Paint.valueOf("FFFF00"));
				}
			}
		}
	}
}