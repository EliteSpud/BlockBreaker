public class Pythagoras
{
	double xSpeed;
	double ySpeed;
	public void calculateSpeeds(double xCoord, double yCoord,double maxX,double maxY,int topCutoff)
	{
		//System.out.println("maxY = "+maxY);
		maxY -= topCutoff;
		double xMidpoint = maxX/2;
		//System.out.println("maxX = "+maxX+" xMidpoint = "+xMidpoint);
		double a = 0; //distance from xMidpoint
		
		//calculating a
		if(xCoord >= xMidpoint)
		{
			a = xCoord - xMidpoint;
			//System.out.println("a = "+a);
			//System.out.println("///////////////////////////////");
		}
		if(xCoord < xMidpoint)
		{
			a = -(xMidpoint - xCoord);
			//System.out.println("a = "+a);
			//System.out.println("///////////////////////////////");
		}
		double b = maxY - yCoord;
		//System.out.println("yCoord = "+yCoord);
		//System.out.println("b = "+b);
		//System.out.println("///////////////////////////////");
		
		xSpeed = a/15;
		ySpeed = b/15;
		
		//System.out.println("xSpeed = "+xSpeed);
		//System.out.println("ySpeed = "+ySpeed);
		//System.out.println("///////////////////////////////");
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