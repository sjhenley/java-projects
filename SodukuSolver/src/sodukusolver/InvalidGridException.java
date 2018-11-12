package sodukusolver;

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
