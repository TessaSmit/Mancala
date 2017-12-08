package nl.sogyo.mancala;

public class Player
{
	private Player opponent; 
	boolean currentPlayer;
	private RegularBox firstBox; 

	public Player(Player opponent, RegularBox firstBox, boolean currentPlayer)
	{
		this.opponent = opponent;
		this.currentPlayer = currentPlayer;
		this.firstBox = firstBox;
	}

	void addOpponent(Player opp)
	{
		if(opponent == null)
		{
			this.opponent = opp;
		}
	}

	void switchPlayer()
	{
		currentPlayer = !(this.getCurrentPlayer() == this);
	}

	public Player getCurrentPlayer()
	{
		if(currentPlayer == true)
		{
			return this;
		}
		else
		{
			return this.getOpponent();
		}
	}

	public Player getOpponent()
	{
		return opponent;
	}

	public RegularBox getFirstBox()
	{
		return firstBox;
	}	

	public Box findKalaha()
	{
		Box myBox = firstBox;
		while(!(myBox instanceof Kalaha) || !(checkIfBoxIsOfPlayer(myBox)))
		{
			myBox = myBox.getNeighbor();
		}
		return myBox;
	}

	private boolean checkIfBoxIsOfPlayer(Box myBox)
	{
		return this == myBox.getPlayerOfBox();
	}

	public boolean checkEndGame()
	{
		boolean[] winnerAndLoser = checkPlayerField();

		return winnerAndLoser[0] || winnerAndLoser[1];
	}

	public boolean[] checkPlayerField()
	{
		boolean player1Wins = firstBox.isNeighborEmpty(firstBox);
		boolean player2Wins = firstBox.isNeighborEmpty(firstBox.getBox(7));
		boolean[] winnerAndLoser = {player1Wins, player2Wins};
		return winnerAndLoser;
	}

	private Player decideWinner()
	{
		if(checkEndGame())
		{
			boolean[] winnerAndLoser = checkPlayerField();
			if( winnerAndLoser[0] )
			{
				return this.getPlayer1();
			}
			else
			{
				return this.getPlayer2();
			}
		}
		return null;
	}

	public int[] getScores()
	{
		int totalStones1 = 24; 
		int totalStones2 = 24; 
		int maxNumStones = 48;

		if(this.decideWinner() != null)
		{
			if(this.decideWinner().equals(this.firstBox.getPlayerOfBox()))
			{
				totalStones1 = this.firstBox.getKalaha(firstBox).getNumStones();
				totalStones2 =  maxNumStones - totalStones1;
			}
			else if(this.decideWinner().equals(this.firstBox.getPlayerOfBox().getOpponent())) 
			{
				totalStones2 = this.firstBox.getKalaha(firstBox.getBox(7)).getNumStones();
				totalStones1 =  maxNumStones - totalStones2;
			}
		}

		int[] scores = {totalStones1, totalStones2};

		return scores;
	}

	public Player getPlayer1()
	{
		return firstBox.getPlayerOfBox();
	}

	public Player getPlayer2()
	{
		return firstBox.getPlayerOfBox().getOpponent();
	}
}