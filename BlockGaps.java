import java.util.Random;
import java.util.ArrayList;

public class BlockGaps
{
	ArrayList<Boolean> blockGaps = new ArrayList<Boolean>();
	public void gaps(int numBlocks)
	{
		Random r = new Random();
		int numGenerated;
		for(int i = 0; i < numBlocks; i++)
		{
			numGenerated = r.nextInt(5); //0 to 4 inclusive
			if(numGenerated > 2) //40% chance of this happening
			{
				blockGaps.add(true);
			}
			else
			{
				blockGaps.add(false);
			} //so there should be, on average, a 60% chance of each available block existing
		}
	}
	public ArrayList getBlockGaps()
	{
		return blockGaps;
	}
}