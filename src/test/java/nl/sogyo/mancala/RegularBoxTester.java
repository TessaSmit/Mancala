package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class RegularBoxTester 
{
	
	@Test
	public void isChosenBoxEmpty() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		RegularBox myBox = new RegularBox(numStonesPerBox);
		int label = 0;
		myBox.move(label);
		Assert.assertTrue(myBox.boxIsEmpty());
	}

	@Test
	public void hasNeighborOneMoreStone() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		int expectedNumStones = 5;
		int label = 0;
		RegularBox myBox = new RegularBox(numStonesPerBox);
		myBox.move(label);
		Assert.assertTrue(expectedNumStones == myBox.getNeighbor().getNumStones());
	}

	@Test
	public void isBoxOfNeighborEmptyAfterMove() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		int label = 0;
		int expectedNumStones = 0;
		Box myBox = new RegularBox(numStonesPerBox);
		myBox.getNeighbor().move(label);
		int stones = myBox.getNeighbor().getNumStones();
		Assert.assertTrue(stones == expectedNumStones);
	}


	@Test
	public void hasFifthNeighbourTheIntialNumOfStones() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		int expectedNumStones = 4;
		int label = 0;
		RegularBox myBox = new RegularBox(numStonesPerBox);
		myBox.move(label);
		Assert.assertTrue(expectedNumStones == myBox.getBox(5).getNumStones());
	}

	@Test
	public void moveStonesOfParticularBox() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		int expectedNumStones = 0;
		int particularBox = 4;
		RegularBox myBox = new RegularBox(numStonesPerBox);
		myBox.move(particularBox);
		myBox.getPlayerOfBox().switchPlayer();
		int stones = myBox.getBox(particularBox).getNumStones();
		Assert.assertTrue(stones == expectedNumStones);
	}

	@Test
	public void checkIfBox6CannotMove()
	{
		int numStonesPerBox = 4;
		int particularBox = 6;
		boolean expected = false;
		RegularBox myBox = new RegularBox(numStonesPerBox);
		try
		{
			myBox.move(particularBox);
		} 
		catch (CannotMoveBoxException e) 
		{
			expected = true;
		}
		Assert.assertTrue(expected);
	}
	
	@Test
	public void checkHittingABox() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox);
		Box kalaha = myBox.getBox(6);
		int expectedNum = 8;
		myBox.move(4);
		myBox.move(7);
		myBox.move(0);
		int totalStones = kalaha.getNumStones();
		Assert.assertTrue(expectedNum == totalStones);
	}

	@Test 
	public void checkWhileHittingIfEnemyBoardStaysTheSame() throws CannotMoveBoxException
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox);
		int expectedValueOfBox9 = 5;
		myBox.move(4);
		myBox.move(7);
		myBox.move(0);
		Assert.assertTrue(expectedValueOfBox9 == myBox.getBox(9).getNumStones());
	}
}
