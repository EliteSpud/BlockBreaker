import javafx.scene.shape.Line;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;

public class Fire
{
	Line line;
	Pane leftPane;
	public void aim(double x,double y,BuildGUI bg)
	{
		System.out.println("aim :"+x+","+y);
		line = bg.getLine();
		line.setStartX((double)bg.getBallCenterX());
		line.setStartY((double)bg.getBallCenterY());
		line.setEndX(x);
		line.setEndY(y);
		line.setStroke(Paint.valueOf("DDDDDD"));
		
		leftPane = bg.getLeftPane();
		leftPane.getChildren().remove(line);	
		leftPane.getChildren().add(line);	
		//bg.getLeftPane().getChildren().remove(line);
		//bg.getLeftPane().getChildren().add(line);
	}
	public void fire()
	{
		
	}
}