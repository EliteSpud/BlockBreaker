import javafx.scene.shape.Line;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;

public class Aim
{
	Line line;
	Pane leftPane;
	int fireX;
	int fireY;
	public void drawLine(double x,double y,BuildGUI bg)
	{
		System.out.println("drawLine :"+x+","+y);
		line = bg.getLine();
		line.setStartX((double)bg.getBallCenterX());
		line.setStartY((double)bg.getBallCenterY());
		line.setEndX(x);
		line.setEndY(y);
		line.setStroke(Paint.valueOf("DDDDDD"));
		
		leftPane = bg.getLeftPane();
		leftPane.getChildren().remove(line);	
		leftPane.getChildren().add(line);	
		
		bg.setAimed(true);	
	}
}