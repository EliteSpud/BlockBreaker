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
	double xSpeed = 5.0; //controls how fast the ball moves horizontally
	double ySpeed = -3.0; //controls how fast the ball moves vertically
	//Bounce bounce = new Bounce();
	
	public void fire(BuildGUI bg)
	{
		balls = bg.getBalls();
		line = bg.getLine();
		fireX = line.getEndX();
		fireY = line.getEndY();
		/*for(int i = 1; i < balls.size(); i++)
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
		}*/

		//for(int i = 0; i < 50; i++)
		while(true)
		{
			balls.get(0).setCenterX(balls.get(0).getCenterX() + xSpeed);
			balls.get(0).setCenterY(balls.get(0).getCenterY() + ySpeed);
			
			
			if((balls.get(0).getCenterX() + 5) > bg.getLeftPane().getWidth())
			{
				bounce("right");
				try{Thread.sleep(25);}
				catch(InterruptedException ie){ie.printStackTrace();}
				System.out.println("x speed is : "+xSpeed);
			}
			if((balls.get(0).getCenterX() - 5) <= 0)
			{
				bounce("left");
				try{Thread.sleep(25);}
				catch(InterruptedException ie){ie.printStackTrace();}
				System.out.println("x speed is : "+xSpeed);
			}
			if((balls.get(0).getCenterY() - 5) <= 0)
			{
				bounce("top");
				try{Thread.sleep(25);}
				catch(InterruptedException ie){ie.printStackTrace();}
				System.out.println("y speed is : "+ySpeed);
			}
			if((balls.get(0).getCenterY() + 5) > bg.getLeftPane().getHeight())
			{
				bottom(bg); //remove the ball from play
				try{Thread.sleep(25);}
				catch(InterruptedException ie){ie.printStackTrace();}
			}
			
			try
			{
				Thread.sleep(5); //controls how fast the loop goes around
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	public void bounce(String side)
	{
		if(side.equals("right") || side.equals("left"))
		{
			xSpeed = -xSpeed;
		}
		if(side.equals("top"))
		{
			ySpeed = -ySpeed;
		}
	}
	public void bottom(BuildGUI bg)
	{
		bg.getLeftPane().getChildren().remove(balls.get(0));
	}
	public void setXSpeed(double speed)
	{
		xSpeed = speed;
	}
	public void setYSpeed(double speed)
	{
		ySpeed = speed;
	}
	public double getXSpeed()
	{
		return xSpeed;
	}
	public double getYSpeed()
	{
		return ySpeed;
	}
}