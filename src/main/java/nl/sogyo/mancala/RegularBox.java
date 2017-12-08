package nl.sogyo.mancala;

public class RegularBox extends Box
{
	public RegularBox(int numStonesPerBox)
	{
		super.numStones = numStonesPerBox;
		int numOfBoxesLeft = 13; 
		this.initPlayers();

		new Kalaha(this, this, numOfBoxesLeft - 1);
	}

	public RegularBox(Box startingBox, Box neighbor, int numOfBoxesLeft)
	{	
		super.numStones = startingBox.getNumStones();
		this.addPlayer(startingBox);
		this.addNeighbor(neighbor);
		addToChain(startingBox, this, numOfBoxesLeft);
	}

	@Override
	public boolean move(int boxLabel) throws CannotMoveBoxException
	{
		if(CanMoveBox(boxLabel))
		{
			throw new CannotMoveBoxException();
		}
		else
		{
			Box boxAtRightPosition = this.getBox(boxLabel);


			if(boxAtRightPosition instanceof RegularBox)
			{
				((RegularBox) boxAtRightPosition).moveStones();
			}
		}
		return this.getPlayerOfBox().checkEndGame();
	}

	private boolean CanMoveBox(int boxLabel)
	{
		boolean isKalaha = (boxLabel == 13) ||  (boxLabel == 6);
		boolean isOpponentBox = false;

		if(this.getBox(boxLabel).getPlayerOfBox() != this.getPlayerOfBox().getCurrentPlayer())
		{
			isOpponentBox = true;
		}

		return isKalaha || isOpponentBox;
	}
	
	private void initPlayers()
	{
		Player player1 = new Player(null,this,true);
		Player player2 = new Player(player1, this,false);
		player1.addOpponent(player2);

		this.addPlayer(player1);
	}

	private void moveStones()
	{
		int numStonesCopy = this.numStones;
		this.numStones = 0;
		if(numStonesCopy > 0)
		{
			this.getNeighbor().passStones(numStonesCopy - 1);
		}
	}

	void passStones(int stones)
	{	
		this.addStone();

		if(stones > 0 )
		{
			this.getNeighbor().passStones(stones - 1);
		}

		if(didIHit(stones))
		{
			hit();
		}

		if(stones == 0)
		{
			this.getPlayerOfBox().switchPlayer();
			this.getPlayerOfBox().getOpponent().switchPlayer();
		}
	}

	private boolean didIHit(int stones)
	{
		boolean myBox = this.getPlayerOfBox() == this.getPlayerOfBox().getCurrentPlayer();
		boolean oneStone = (stones == 0) && (this.getNumStones() == 1);
		boolean regularBox = this instanceof RegularBox;

		return myBox && oneStone && regularBox;
	}

	private void hit()
	{
		Box opposite = this.getOppositeBox();
		((RegularBox)opposite).passToOpposite(this.numStones);
		this.numStones = 0; 
	}
	
	private Box getOppositeBox()
	{
		int countToKalaha = 0;
		Box currentBox = this;

		while(!(currentBox instanceof Kalaha))
		{
			countToKalaha++;
			currentBox = currentBox.getNeighbor();
		}

		for(int i = 0; i < countToKalaha; i++)
		{
			currentBox = currentBox.getNeighbor();
		}
		return currentBox;
	}

	private void passToOpposite(int firstPartStones)
	{
		int totalStones = firstPartStones + this.numStones; 
		this.numStones  = 0; 

		this.toKalaha(totalStones);
	}

	@Override
	void toKalaha(int totalStones)
	{
		Box myKalaha = this.getPlayerOfBox().getCurrentPlayer().findKalaha();
		myKalaha.toKalaha(totalStones);
	}
}