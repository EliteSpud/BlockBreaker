public class Round
{
	Main m = new Main();
	public int next()
	{
		int currentRound = m.getCurrentRound();
		currentRound++;
		m.setCurrentRound(currentRound);
		currentRound = m.getCurrentRound();
		return currentRound;
	}
}