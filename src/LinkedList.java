public class LinkedList {
	private TTTRecord record;
	private LinkedList next;
	
	/*
	 * Method: LinkedList 
	 * Accepts: N/A
	 * Returns: N/A
	 * Description: Constructor, initializes variables 
	 */
	public LinkedList(){
		record = null;
		next = null;
	}
	
	/*
	 * Method: add
	 * Accepts: TTTRecord
	 * Returns: N/A
	 * Description: initializes record 
	 */
	public void add(TTTRecord rec){
		record = rec;
	}
	
	/*
	 * Method: getRecord
	 * Accepts: N/A
	 * Returns: TTTRecord
	 * Description: returns a TTTRecord associated to the LinkedList
	 */
	public TTTRecord getRecord(){
		return record;
	}
	
	/*
	 * Method: setNext
	 * Accepts: LinkedList
	 * Returns: N/A
	 * Description: Points the current LinkedList to the LinkedList
	 * 				sent in as an argument 
	 */
	public void setNext(LinkedList next_node){
		next = next_node;
	}
	
	/*
	 * Method: getNext
	 * Accepts: N/A
	 * Returns: LinkedList
	 * Description: gets next LinkedList associated to the current
	 * 				LinkedList
	 */
	public LinkedList getNext(){
		return next;
	}
	
}
