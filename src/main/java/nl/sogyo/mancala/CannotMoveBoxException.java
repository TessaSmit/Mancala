package nl.sogyo.mancala;

public class CannotMoveBoxException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public CannotMoveBoxException()
	{
		super();
	}
	
	public CannotMoveBoxException(Throwable cause)
	{
		super(cause);
	}
	
	public CannotMoveBoxException(String message)
	{
		super(message);
	}
}