package nl.sogyo.mancala;

public class Kalaha extends Box {
	public Kalaha(Box startingBox, Box neighbor, int numOfBoxesLeft)
	{
		startingBox.getPlayerOfBox().switchPlayer();
		this.addPlayer(startingBox);
		this.addNeighbor(neighbor);
		addToChain(startingBox, this, numOfBoxesLeft);
	}

	@Override
	boolean move(int boxLabel) 
	{
		return false;	
	}

	@Override
	void passStones(int stones) 
	{	
		if(kalahaBelongsToCurrentPlayer())
		{
			this.addStone();
		}
		
		if(stones > 0)
		{
			this.getNeighbor().passStones(stones - 1);
		}
		
		else if(stones > 0 && !(kalahaBelongsToCurrentPlayer()))
		{
			this.getNeighbor().passStones(stones);
		}
	}
	
	private boolean kalahaBelongsToCurrentPlayer()
	{
		return this.getPlayerOfBox() == this.getPlayerOfBox().getFirstBox().getPlayerOfBox().getCurrentPlayer();
	}

	@Override
	void toKalaha(int totalScore) 
	{
		this.numStones = this.numStones + totalScore;
	}
}