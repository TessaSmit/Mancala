package nl.sogyo.mancala;

public abstract class Box
{
	private Box neighbor;
	int numStones = 0;
	private Player boxOfPlayer;

	void addToChain(Box startingBox, Box neighbor, int numOfBoxesLeft)
	{
		if(numOfBoxesLeft == 6)
		{
			new Kalaha(startingBox, neighbor, numOfBoxesLeft - 1);
		}
		else if(numOfBoxesLeft > 0)
		{
			new RegularBox(startingBox, neighbor, numOfBoxesLeft - 1); 
		}
		else
		{
			linkLastAndFirst(startingBox);
		}
	}

	private void linkLastAndFirst(Box startingBox)
	{
		startingBox.neighbor = this;
	}

	void addNeighbor(Box neighbor)
	{
		this.neighbor = neighbor;
	}

	void addPlayer(Box startingBox)
	{
		if(boxOfPlayer == null)
		{
			this.boxOfPlayer = startingBox.getPlayerOfBox().getCurrentPlayer();
		}
	}

	void addPlayer(Player player)
	{
		if(boxOfPlayer == null)
		{
			this.boxOfPlayer = player;
		}
	}

	public boolean boxIsEmpty()
	{
		if(numStones != 0)
		{
			return false;
		}
		return true;
	}

	public Box getNeighbor()
	{	
		return neighbor;
	}

	public int getNumStones()
	{	
		return numStones;
	}	

	public Player getPlayerOfBox()
	{
		return boxOfPlayer;
	}

	void addStone()
	{
		numStones = numStones + 1;
	}

	abstract void passStones(int stones);
	
	private Box loopTillRightBox(int boxLabel, Box currentBox)
	{
		for(int i = 0; i < boxLabel; i++)
		{
			currentBox = currentBox.getNeighbor();
		}
		return currentBox;
	}
	
	public Box getBox(int boxLabel)
	{
		Box currentBox = this;
		
		currentBox = loopTillRightBox(boxLabel, currentBox);
		return currentBox;
	}

	boolean isNeighborEmpty(Box startingBox)
	{
		boolean done = false;
		if((startingBox instanceof Kalaha))
		{
			return true;
		}
		if(startingBox.getNumStones() == 0)
		{
			Box neighbor = startingBox.getNeighbor();
			done = this.isNeighborEmpty(neighbor);
		}
		else
		{
			return false;
		}
		return done;
	}
	
	Kalaha getKalaha(Box startingBox)
	{
		while(!(startingBox instanceof Kalaha))
		{
			startingBox = startingBox.getNeighbor();
		}
		return (Kalaha) startingBox; 
	}
	
	abstract boolean move(int boxLabel) throws CannotMoveBoxException;
	abstract void toKalaha(int totalScore);
}