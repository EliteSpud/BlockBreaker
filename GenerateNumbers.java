import java.util.Random;
import java.util.ArrayList;

public class GenerateNumbers
{
	int totalRows = 6;
	int initialRows = 4;
	int currentRows = initialRows;
	
	public ArrayList generate(int level,int high,int low)
	{
		ArrayList<Integer> numList = new ArrayList<Integer>();
		Random r = new Random();
		
		if(level == 1)
		{
			for(int i = 0; i < (totalRows*8); i++)
			{
				numList.add(i,r.nextInt(high-low)+low); //generates a number between high (exclusive) and low (inclusive), adds it to ArrayList
			}
		}
		
		return numList;
	}
	public int getInitialRows()
	{
		return initialRows;
	}
	public int getTotalRows()
	{
		return totalRows;
	}
	public int getCurrentRows()
	{
		return currentRows;
	}
	public void setCurrentRows(int setCurrentRows)
	{
		currentRows = setCurrentRows;
	}
}