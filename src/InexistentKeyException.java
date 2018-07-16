
public class InexistentKeyException extends Exception {
	
	/*
	 * Method: InexistentKeyException
	 * Accepts: N/A
	 * Returns: N/A
	 * Description:  Constructor, if exception is thrown the user is alerted of error 
	 */
	public InexistentKeyException(){
		super("Configuration is not in dictionary");
	}
}
