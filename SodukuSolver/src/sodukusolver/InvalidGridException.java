package sodukusolver;

/**
 * Exception thrown when an invalid grid is passed to the sorting algorithm.
 * @author Sam Henley
 *
 */
@SuppressWarnings("serial")
public class InvalidGridException extends RuntimeException
{
	public InvalidGridException()
	{
		this(null);
	}
	
	public InvalidGridException(String message)
	{
		super(message);
	}
}
