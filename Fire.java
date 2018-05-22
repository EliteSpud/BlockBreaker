import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
			int topCutoff = (totalRows-initialRows)*49; //the height of the leftPane that is actually above the visible area
			
			Pythagoras p = new Pythagoras(); //doesn't actually do Pythagoras. It did originally. Needs renaming.
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
					ObservableList<Node> stacks = bg.getGridGame().getChildren(); //list of nodes of type stackPane (children of gridGame), however they're stored as Node
					ArrayList<StackPane> sPanes = new ArrayList<StackPane>(); //used to store the nodes in 'stacks', but actually as the StackPane datatype and not just the generic Node
					boolean inGridBounds;
					boolean inStackBounds;
					ArrayList<Circle> ballsToMove = new ArrayList<Circle>();
					
					for(int i = 0; i < balls.size(); i++) //loops through each ball that has not yet reached the bottom
					{
						if(ballsToMove.contains(balls.get(i)) == false)
						{
							ballsToMove.add(balls.get(i));
						}
						for(int q = 0; q < ballsToMove.size(); q++)
						{
							if(ballsInPlay.get(q) == true) //if ball has not hit the bottom
							{
								ballsToMove.get(q).setLayoutX(balls.get(q).getLayoutX() + xSpeed[q]); //moving the balls
								ballsToMove.get(q).setLayoutY(balls.get(q).getLayoutY() - ySpeed[q]); //-y as y increases downwards
							
								boolean atRightBorder = balls.get(q).getLayoutX() >= (maxX/2 - balls.get(q).getRadius());
								boolean atLeftBorder = balls.get(q).getLayoutX() <= (-(maxX/2) + balls.get(q).getRadius());
								boolean atBottomBorder = (balls.get(q).getLayoutY() > (balls.get(q).getRadius()));
								boolean atTopBorder = balls.get(q).getLayoutY() <= (-(maxY-topCutoff) - balls.get(q).getRadius());
								//System.out.println("minX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
								//System.out.println("maxX = "+maxX+" ballX = "+balls.get(i).getLayoutX()); //debug
								//System.out.println("minY = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
								//System.out.println("maxy = "+maxY+" ballY = "+ -(balls.get(i).getLayoutY())); //debug
								if(atRightBorder || atLeftBorder)
								{
									xSpeed[q] *= -1; //reverses the xSpeed, so the ball travels in the opposite direction in the x axis
									
									//System.out.println("atRightBorder = "+atRightBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
									//System.out.println("atLeftBorder = "+atLeftBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
								}
								if(atTopBorder) 
								{
									ySpeed[q] *= -1; //reverses the ySpeed, so the ball travels in the opposite direction in the y axis
									
									//System.out.println("atTopBorder = "+atTopBorder+" position = "+balls.get(i).getLayoutX()+","+balls.get(i).getLayoutY()); //debug
								}
								if(atBottomBorder)
								{
									ballsInPlay.set(q,false); //remove this ball from play
									balls.get(q).setLayoutX(0.0); //return ball to original position
									balls.get(q).setLayoutY(0.0);
									//System.out.println("atBottomBorderBorder = "+atBottomBorder); //debug
									
									//////////////////////////////////////////////////////////////////////
									canFire = true; //should only be called when all balls are out of play
									//////////////////////////////////////////////////////////////////////
								}
								//System.out.println("/////////////////////"); //debug
								ballHitBox = balls.get(q).getBoundsInParent();
								inGridBounds = gridBounds.intersects(ballHitBox); //checks if the ball is somewhere in the gridPane containing the blocks
								//System.out.println("inGridBounds = "+inGridBounds); //debug
								if(inGridBounds == true)
								{
									int s = 0;
									for(Node stack : stacks) //for every Node 'stack' in ObservableList 'stacks'
									{
										if(stack instanceof StackPane)
										{
											sPanes.add((StackPane)stack);
										}
										stackBounds = stack.getBoundsInParent();
										inStackBounds = stackBounds.intersects(ballHitBox);
										if(inStackBounds) //if ball is in the bounds of a StackPane (i.e. if a ball is touching or inside of a block)
										{
											int col = GridPane.getColumnIndex(stack);
											int row = GridPane.getRowIndex(stack);
											
											ObservableList<Node> stackChildren = sPanes.get(s).getChildren(); //gets the children of the stackPane (Rectangle,Text)
											for(Node node : stackChildren) //for every Node 'node' in ObservableList 'stackChildren'
											{
												Text txt = new Text();
												if(node instanceof Text)
												{
													txt = (Text)node;
													if(GridPane.getColumnIndex(node.getParent()) == col && GridPane.getRowIndex(node.getParent()) == row)
													{
														if(txt.getText().equals("")==false)
														{
															try{txt.setText(String.valueOf(Integer.parseInt(txt.getText()) - 1));}
															catch(NumberFormatException p){p.printStackTrace();System.out.println("/////////////");}
														}
													}
												}
												try
												{
													if(txt.getText().equals("") == false)
													{
														if(Integer.parseInt(txt.getText()) <= 0)
														{
															node.setVisible(false); //this is used to set the text to be invisible. it does not work for the rectangle.
															stackChildren.set(0,new Rectangle(49,49)); //sets the StackPane at index 0 (the rectangle) to be a new blank rectangle of the same size.
																									   //this makes it so it appears that the rectangle has been broken or has disappeared
															sPanes.get(s).setId("broken");
														}
													}
												}
												catch(Exception p){p.printStackTrace();}
											}
											
											//layoutX and layoutY are taken from the top-left of the StackPane
											if(((StackPane)stack).getId().equals("broken")==false)
											{
												double stackX = ((StackPane)stack).getLayoutX();
												double stackY = ((StackPane)stack).getLayoutY();
												System.out.println("stackX = "+stackX+" stackX +49 = "+(stackX+49)+" stackY = "+stackY+" stackY + 49 = "+(stackY+49)+ " ballX = "+(balls.get(q).getLayoutX() + (balls.get(q).getRadius() * 2) + maxX/2)+" ballY = "+(balls.get(q).getLayoutY() + (maxY-topCutoff)));
												if(balls.get(q).getLayoutX() + (balls.get(q).getRadius() * 2) + maxX/2 >= stackX
												&& (balls.get(q).getLayoutX() + maxX/2) < stackX && xSpeed[q] > 0)
												{
													System.out.println("hit left of block");
													xSpeed[q] *= -1;
												}
												else if(balls.get(q).getLayoutX() + maxX/2 <= stackX+50
												&& (balls.get(q).getLayoutX() + maxX/2 > stackX + 40) && xSpeed[q] < 0)
												{
													System.out.println("hit right of block");
													xSpeed[q] *= -1;
												}
												else if((balls.get(q).getLayoutY() /*- (balls.get(q).getRadius() * 2)*/ + (maxY-topCutoff)) >= stackY && ySpeed[q] > 0)
												{
													System.out.println("HIT BOTTOM");
													ySpeed[q] *= -1;
												}
												else if(balls.get(q).getLayoutY() + (maxY-topCutoff) <= stackY-49 && balls.get(q).getLayoutX() + (maxY - topCutoff) > stackY+39 && ySpeed[q] < 0)
												{
													System.out.println("HIT TOP");
													ySpeed[q] *= -1;
												}
											}
										}
									s++;
									}
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