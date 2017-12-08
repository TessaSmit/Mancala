package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BoxTester 
{
	@Test
	public void hasBoxNeighbor()
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox );
		Assert.assertTrue(myBox.getNeighbor() != null);
	}
	@Test
	public void hasNeighborBoxANeighbor()
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox );
		Assert.assertTrue(myBox.getNeighbor().getNeighbor() != null);
	}
	@Test
	public void checkNumStonesNeighbor()
	{
		int numStonesPerBox = 4;
		Box myBox = new RegularBox(numStonesPerBox );
		Box myNeighbor = myBox.getNeighbor();
		int expectedNumStones = 4;
		Assert.assertTrue(expectedNumStones == myNeighbor.getNumStones() );	
	}
	@Test
	public void checkIfBoxesAreFromTheSamePlayer()
	{
		int numStonesPerBox = 4;
		RegularBox myBox = new RegularBox(numStonesPerBox );
		Box box1 = myBox.getBox(0);
		Box box2 = myBox.getBox(5);
		
		Assert.assertTrue(box1.getPlayerOfBox() == box2.getPlayerOfBox());
	}
	@Test
	public void checkIfBoxesAreFromOfDifferentPlayers()
	{
		int numStonesPerBox = 4;
		RegularBox myBox = new RegularBox(numStonesPerBox );
		Box box1 = myBox.getBox(1);
		Box box2 = myBox.getBox(7);
		
		Assert.assertTrue(box1.getPlayerOfBox() != box2.getPlayerOfBox());
	}
	@Test
	public void check14And0Equality()
	{
		int numStonesPerBox = 4;
		RegularBox myBox = new RegularBox(numStonesPerBox );
		Box box1 = myBox.getBox(0);
		Box box2 = myBox.getBox(14);
		Assert.assertTrue(box1 == box2);
	}
}
