import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.text.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;

public class Fire
{
	Line line;
	double fireX; //x position of the end of the aiming line
	double fireY; //y position of the end of the aiming line
	ArrayList<Circle> balls;
	double xSpeed[]; //controls how fast the ball moves horizontally
	double ySpeed[]; //controls how fast the ball moves vertically
	boolean canFire = true;
	
	public void fire(BuildGUI bg,GenerateNumbers gn)
	{
		if(canFire == true)
		{
			canFire = false;
			balls = bg.getBalls();
			
			Bounds gridBounds = bg.getGridGameBounds();
			//System.out.println("gridBounds = "+gridBounds); //debug
			Bounds paneBounds = bg.getLeftPane().getBoundsInParent();
			double maxX = paneBounds.getMaxX();
			double maxY = paneBounds.getMaxY();
			
			line = bg.getLine();
			fireX = line.getEndX();
			//System.out.println("fireX : "+fireX); //debug
			fireY = line.getEndY();
			//System.out.println("fireY : "+fireY); //debug
			xSpeed = new double[balls.size()];
			ySpeed = new double[balls.size()];
			int initialRows = gn.getInitialRows();
			int totalRows = gn.getTotalRows();
			int topCutoff = (totalRows-initialRows)*49;
			
			Pythagoras p = new Pythagoras();
			p.calculateSpeeds(fireX,fireY,maxX,maxY,topCutoff);
			
			double defXSpeed = p.getXSpeed(); //default x speed is +5 (right)
			double defYSpeed = p.getYSpeed(); //default y speed is -3 (up)
			//System.out.println("defXSpeed = "+defXSpeed); //debug
			//System.out.println("defYSpeed = "+defYSpeed); //debug
			//System.out.println("///////////////////////////////"); //debug
			double paneHeight = bg.getLeftPane().getHeight();
			double paneWidth = bg.getLeftPane().getWidth();
			int count = 1;
			
			for(int i = 0; i < balls.size(); i++)
			{
				xSpeed[i] = defXSpeed;
				ySpeed[i] = defYSpeed;
				//System.out.println("xSpeed["+i+"] = "+xSpeed[i]); //debug
				//System.out.println("ySpeed["+i+"] = "+ySpeed[i]); //debug
			}
			
			//System.out.println("initial ballX = "+balls.get(0).getLayoutX()); //debug
			//System.out.println("initial ballY = "+balls.get(0).getLayoutY()); //debug
			
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
					Bounds ballHitBox;
					Bounds stackBounds;
					ObservableList<Node> stacks = bg.getGridGame().getChildren(); //list of nodes of type stackPane (children of gridGame)
					ArrayList<StackPane> sPanes = new ArrayList<StackPane>();
					Text txt;
					boolean inGridBounds;
					boolean inStackBounds;
					
					for(int i = 0; i < balls.size(); i++)
					{
						if(ballsInPlay.get(i) == true)
						{
							balls.get(i).setLayoutX(balls.get(i).getLayoutX() + xSpeed[i]);
							balls.get(i).setLayoutY(balls.get(i).getLayoutY() - ySpeed[i]);
						
							boolean atRightBorder = balls.get(i).getLayoutX() >= (maxX/2 - balls.get(i).getRadius());
							boolean atLeftBorder = balls.get(i).getLayoutX() <= (-(maxX/2) + balls.get(i).getRadius());
							
							boolean atBottomBorder = (balls.get(i).getLayoutY() > (balls.get(i).getRadius()));
							
							boolean atTopBorder = balls.get(i).getLayoutY() <= (-(maxY-topCutoff) - balls.get(i).getRadius());
							//System.out.println("minX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
							//System.out.println("maxX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
							//System.out.println("minY = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
							//System.out.println("maxy = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
							if(atRightBorder || atLeftBorder)
							{
								xSpeed[i] *= -1;
								//System.out.println("atRightBorder = "+atRightBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
								//System.out.println("atLeftBorder = "+atLeftBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
							}
							if(atTopBorder) 
							{
								ySpeed[i] *= -1;
								//System.out.println("atTopBorder = "+atTopBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
							}
							if(atBottomBorder)
							{
								ballsInPlay.set(i,false);
								balls.get(i).setLayoutX(0.0);
								balls.get(i).setLayoutY(0.0);
								//System.out.println("atBottomBorderBorder = "+atBottomBorder); //debug
								canFire = true;
							}
							//System.out.println("/////////////////////"); //debug
							ballHitBox = balls.get(i).getBoundsInParent();
							inGridBounds = gridBounds.intersects(ballHitBox); //checks if the ball is somewhere in the gridPane containing the blocks
							//System.out.println("inGridBounds = "+inGridBounds); //debug
							if(inGridBounds == true)
							{
								int s = 0;
								for(Node stack : stacks)
								{
									if(stack instanceof StackPane)
									{
										sPanes.add((StackPane)stack);
									}
									stackBounds = stack.getBoundsInParent();
									inStackBounds = stackBounds.intersects(ballHitBox);
									if(inStackBounds)
									{
										int col = GridPane.getColumnIndex(stack);
										int row = GridPane.getRowIndex(stack);
										
										ObservableList<Node> stackChildren = sPanes.get(s).getChildren();
										for(Node node : stackChildren)
										{
											if(node instanceof Text)
											{
												txt = (Text)node;
												if(GridPane.getColumnIndex(node.getParent()) == col && GridPane.getRowIndex(node.getParent()) == row)
												{
													try{txt.setText(String.valueOf(Integer.parseInt(txt.getText()) - 1));}
													catch(NumberFormatException p){p.printStackTrace();}
												}
											}
										}
									}
								s++;
								}
							}
						}
					}
				}
			}));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
		}
	}
}