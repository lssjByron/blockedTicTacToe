
public class DuplicatedKeyException extends Exception {
	
	/*
	 * Method: DuplicatedKeyException
	 * Accepts: N/A
	 * Returns: N/A
	 * Description: Constructor, if exception is thrown the user is alerted of error 
	 */
	public DuplicatedKeyException(){
		super("collision detected");
	}
}
