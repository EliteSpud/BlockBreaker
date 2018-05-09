import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class Fire
{
	Line line;
	double fireX;
	double fireY;
	ArrayList<Circle> balls;
	double xSpeed = 5.0;
	double ySpeed = -5.0;
	public void fire(BuildGUI bg)
	{
		balls = bg.getBalls();
		line = bg.getLine();
		fireX = line.getEndX();
		fireY = line.getEndY();
		for(int i = 1; i < balls.size(); i++)
		{
			for(int k = 0; k < i; k++)
			{
				System.out.println("fire k loop");
				balls.get(i).setCenterX(balls.get(i).getCenterX() + xSpeed);
				balls.get(i).setCenterY(balls.get(i).getCenterY() + ySpeed);
				//balls.get(i).setTranslateX(xSpeed);
				//balls.get(i).setTranslateY(ySpeed);
				
				bg.getLeftPane().getChildren().remove(balls.get(i));
				bg.getLeftPane().getChildren().add(balls.get(i));
				//bg.getScene().show();
			}
			System.out.println("fire i loop");
		}
	}
	public void setXSpeed(double speed)
	{
		xSpeed = speed;
	}
	public void setYSpeed(double speed)
	{
		ySpeed = speed;
	}
}