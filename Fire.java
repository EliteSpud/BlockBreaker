import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;

public class Fire
{
	Line line;
	double fireX;
	double fireY;
	ArrayList<Circle> balls;
	double xSpeed[]; //controls how fast the ball moves horizontally
	double ySpeed[]; //controls how fast the ball moves vertically
	boolean canFire = true;
	
	public void fire(BuildGUI bg,GenerateNumbers gn)
	{
		if(canFire == true);
		{
			canFire = false;
			balls = bg.getBalls();
			line = bg.getLine();
			fireX = line.getEndX();
			fireY = line.getEndY();
			xSpeed = new double[balls.size()];
			ySpeed = new double[balls.size()];
			double defXSpeed = 5.0; //default x speed is +5 (right)
			double defYSpeed = -3.0; //default y speed is -3 (up)
			double paneHeight = bg.getLeftPane().getHeight();
			double paneWidth = bg.getLeftPane().getWidth();
			int count = 1;
			
			for(int i = 0; i < balls.size(); i++)
			{
				xSpeed[i] = defXSpeed;
				ySpeed[i] = defYSpeed;
			}

			//for(int i = 0; i < 50; i++)
			/*while(true)
			{
				for(int i = 0; i < count; i++)
				{
					//bg.getRoot().requestLayout();
					//System.out.println("top of loop");				
					//System.out.println(i);
					try //in a try and catch cause it wasn't working
					{
						balls.get(i).setCenterX(balls.get(i).getCenterX() + xSpeed[i]);
						balls.get(i).setCenterY(balls.get(i).getCenterY() + ySpeed[i]);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					if((balls.get(i).getCenterX() + 5) > paneWidth)
					{
						bounce("right",i);
						try{Thread.sleep(2);}
						catch(InterruptedException ie){ie.printStackTrace();}
						System.out.println("x speed is : "+xSpeed[i]);
					}
					if((balls.get(i).getCenterX() - 5) <= i)
					{
						bounce("left",i);
						try{Thread.sleep(2);}
						catch(InterruptedException ie){ie.printStackTrace();}
						System.out.println("x speed is : "+xSpeed[i]);
					}
					if((balls.get(i).getCenterY() - 5) <= i)
					{
						bounce("top",i);
						try{Thread.sleep(2);}
						catch(InterruptedException ie){ie.printStackTrace();}
						System.out.println("y speed is : "+ySpeed[i]);
					}
					if((balls.get(i).getCenterY() + 5) > paneHeight)
					{
						bottom(bg); //remove the ball from play
						try{Thread.sleep(2);}
						catch(InterruptedException ie){ie.printStackTrace();}
					}
					try
					{
						Thread.sleep(2); //controls how fast the loop goes around
					}
					catch(InterruptedException ie)
					{
						ie.printStackTrace();
					}
					//System.out.println("bottom of loop");
				}
				if(count < balls.size())
				{
					count++;
				}
			}*/
			
			System.out.println("initial ballX = "+balls.get(0).getLayoutX()); //debug
			System.out.println("initial ballY = "+balls.get(0).getLayoutY()); //debug
			
			Bounds bounds = bg.getLeftPane().getBoundsInParent();
			double maxX = bounds.getMaxX();
			double maxY = bounds.getMaxY();
			int initialRows = gn.getInitialRows();
			int totalRows = gn.getTotalRows();
			int topCutoff = (totalRows-initialRows)*49;
			ArrayList<Boolean> ballsInPlay = new ArrayList<Boolean>();
			for(int i = 0; i < balls.size(); i++)
			{
				ballsInPlay.add(i,true);
			}
			Timeline t = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() 
			{

				@Override
				public void handle(final ActionEvent t) 
				{
					for(int i = 0; i < balls.size(); i++)
					{
						if(ballsInPlay.get(i) == true)
						{
							balls.get(i).setLayoutX(balls.get(i).getLayoutX() + xSpeed[i]);
							balls.get(i).setLayoutY(balls.get(i).getLayoutY() + ySpeed[i]);
						
							boolean atRightBorder = balls.get(i).getLayoutX() >= (maxX/2 - balls.get(i).getRadius());
							boolean atLeftBorder = balls.get(i).getLayoutX() <= (-(maxX/2) + balls.get(i).getRadius());
							boolean atBottomBorder = balls.get(i).getLayoutY() >= (0/*maxY - balls.get(i).getRadius()*/);
							boolean atTopBorder = balls.get(i).getLayoutY() <= (-(maxY-topCutoff) - balls.get(i).getRadius());
							System.out.println("minX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
							System.out.println("maxX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
							System.out.println("minY = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
							System.out.println("maxy = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
							if(atRightBorder || atLeftBorder)
							{
								xSpeed[i] *= -1;
								System.out.println("atRightBorder = "+atRightBorder); //debug
								System.out.println("atLeftBorder = "+atLeftBorder); //debug
							}
							if(atTopBorder) 
							{
								ySpeed[i] *= -1;
								System.out.println("atTopBorder = "+atTopBorder); //debug
							}
							if(atBottomBorder)
							{
								ballsInPlay.set(i,false);
								balls.get(i).setLayoutX(0.0);
								balls.get(i).setLayoutY(0.0);
								System.out.println("atBottomBorderBorder = "+atBottomBorder); //debug
							}
							System.out.println("/////////////////////"); //debug
						}
					}
				}
			}));

        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
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