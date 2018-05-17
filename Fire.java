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
	double xSpeed[]; //controls how fast the ball moves horizontally, 5.0 by default
	double ySpeed[]; //controls how fast the ball moves vertically, -3 by default

	
	public void fire(BuildGUI bg)
	{
		balls = bg.getBalls();
		line = bg.getLine();
		fireX = line.getEndX();
		fireY = line.getEndY();
		int count = 1;
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
			for(int i = 0; i < count; i++)
			{
				//bg.getRoot().requestLayout();
				System.out.println("top of loop");				
				System.out.println(i);
				try //in a try and catch cause it wasn't working
				{
					balls.get(i).setCenterX(balls.get(i).getCenterX() + xSpeed[i]);
					balls.get(i).setCenterY(balls.get(i).getCenterY() + ySpeed[i]);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				if((balls.get(i).getCenterX() + 5) > bg.getLeftPane().getWidth())
				{
					bounce("right",i);
					try{Thread.sleep(25);}
					catch(InterruptedException ie){ie.printStackTrace();}
					System.out.println("x speed is : "+xSpeed);
				}
				if((balls.get(i).getCenterX() - 5) <= i)
				{
					bounce("left",i);
					try{Thread.sleep(25);}
					catch(InterruptedException ie){ie.printStackTrace();}
					System.out.println("x speed is : "+xSpeed);
				}
				if((balls.get(i).getCenterY() - 5) <= i)
				{
					bounce("top",i);
					try{Thread.sleep(25);}
					catch(InterruptedException ie){ie.printStackTrace();}
					System.out.println("y speed is : "+ySpeed);
				}
				if((balls.get(i).getCenterY() + 5) > bg.getLeftPane().getHeight())
				{
					bottom(bg); //remove the ball from play
					try{Thread.sleep(25);}
					catch(InterruptedException ie){ie.printStackTrace();}
				}
				try
				{
					Thread.sleep(10); //controls how fast the loop goes around
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
				System.out.println("bottom of loop");
			}
			count++;
		}
	}
	public void bounce(String side,int pos)
	{
		if(side.equals("right") || side.equals("left"))
		{
			xSpeed[pos] = -xSpeed[pos];
		}
		if(side.equals("top"))
		{
			ySpeed[pos] = -ySpeed[pos];
		}
	}
	public void bottom(BuildGUI bg)
	{
		//bg.getLeftPane().getChildren().remove(balls.get(0));
	}
	public void setXSpeed(double speed,int pos)
	{
		xSpeed[pos] = speed;
	}
	public void setYSpeed(double speed,int pos)
	{
		ySpeed[pos] = speed;
	}
	public double[] getXSpeed()
	{
		return xSpeed;
	}
	public double[] getYSpeed()
	{
		return ySpeed;
	}
}