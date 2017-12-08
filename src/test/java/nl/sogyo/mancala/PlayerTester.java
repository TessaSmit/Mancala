package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTester 
{
	@Test
	public void checkIfTurnDoesNotChange() 
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox);
		Player currentPlayer1 = myBox.getPlayerOfBox().getCurrentPlayer();
		try 
		{
			myBox.move(2);
		}
		catch (CannotMoveBoxException e) {
			e.printStackTrace();
		}
		Player currentPlayer2 = myBox.getPlayerOfBox().getCurrentPlayer();
		Assert.assertTrue(currentPlayer1 == currentPlayer2);
	}

	@Test
	public void checkTurnChange() 
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox);
		Player currentPlayer1 = myBox.getPlayerOfBox().getCurrentPlayer();
		try
		{
			myBox.move(1);
			myBox.move(7);
			myBox.move(4);
		}
		catch (CannotMoveBoxException e)
		{
			e.printStackTrace();
		}
		Player currentPlayer2 = myBox.getPlayerOfBox().getCurrentPlayer();
		Assert.assertTrue(currentPlayer1 != currentPlayer2);
	}


	@Test
	public void checkEndGameOccurs() 
	{
		int numStonesPerBox = 0;
		Box myBox = new RegularBox(numStonesPerBox);
		Player currentPlayer = myBox.getPlayerOfBox().getCurrentPlayer();
		boolean endGame = currentPlayer.checkEndGame();
		Assert.assertTrue(endGame == true);
	}

}
