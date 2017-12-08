package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTester 
{
	@Test
	public void checkIgnoreEnemyKalaha() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox);
		
		Box kalaha = myBox.getPlayerOfBox().findKalaha();
		int expected = 0;
		
		myBox.getBox(11);
		
		int opponentKalaha = kalaha.getNumStones();
		Assert.assertTrue(opponentKalaha == expected);
	}
}
