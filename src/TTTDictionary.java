import java.util.ArrayList;

public class TTTDictionary implements TTTDictionaryADT {
	private int size;
	private int number_of_records;
	private ArrayList<LinkedList> bucket; //bucket is the dictionary
	
	
	/*
	 * Method: put
	 * Accepts: TTTRecord
	 * Returns: int, returns 1 if there is a collision
	 * Description: Creates a LinkedList, adds a record to the LinkedList, and
	 * 				points to the next LinkedList. Also checks for collisions and
	 * 				throws a DuplicatedKeyException if LinkedList containing the
	 * 				same record is being added to the dictionary
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException{
		int dictionary_index = getHashIndex(record.getConfiguration());
		int is_collision = 0;
		//get head node of chain
		LinkedList record_node = bucket.get(dictionary_index);//will have null value if empty
		if(record_node != null){
			is_collision = 1;	
			//check if record is in dictionary
			while(record_node!=null){
				TTTRecord head = record_node.getRecord();
				if(head.getConfiguration().equals(record.getConfiguration())){
					throw new DuplicatedKeyException();
				}
				record_node = record_node.getNext();
			}
			
			//new_record will become head of LinkedList
			record_node = bucket.get(dictionary_index);
			LinkedList new_record = new LinkedList();
			new_record.add(record); //adds record to list
			new_record.setNext(record_node); //sets the next linkedlist which will point to a record
			bucket.set(dictionary_index, new_record);
			number_of_records += 1;
		}
		else{
			//add linkedlist to bucket (dictionary)
			record_node = new LinkedList();
			record_node.add(record);
			bucket.set(dictionary_index, record_node);
			number_of_records += 1;
		}
		return is_collision;
	}
	
	/*
	 * Method: remove
	 * Accepts: String
	 * Returns: N/A
	 * Description: Removes a LinkedList based on the whether the record
	 * 				associated with it matches the configuration sent in 
	 * 				as an argument. Throws an InexistentKeyException if
	 * 				the there is no record that matches the configuration
	 * 				sent in as an argument.
	 */
	public void remove (String config) throws InexistentKeyException{
		int dictionary_index = getHashIndex(config);
		//get head node of chain
		LinkedList record_head_node = bucket.get(dictionary_index);//will have null value if empty
		LinkedList record_previous_node = null;
		
		if(record_head_node != null){
			//check if record is in dictionary
			while(record_head_node!=null){
				TTTRecord head = record_head_node.getRecord();
				if(head.getConfiguration().equals(config)){
					break;
				}
				record_previous_node = record_head_node;
				record_head_node = record_head_node.getNext();
			}
			if(record_head_node == null){
				throw new InexistentKeyException();
			}
			
			if(record_previous_node != null){
				//sets the previous record (in relation to the current head node)
				//to point to the next record thus eliminating the head node from
				//the list. ex(prev_record->current_record->next_record,
				//prev_record.next = current_record.next, prev_record->next_record)
				record_previous_node.setNext(record_head_node.getNext());
			}
			else
				bucket.set(dictionary_index, record_head_node.getNext());//will remove head by setting the new head to head.next
		}
		else
			throw new InexistentKeyException();
	}
	
	/*
	 * Method: get
	 * Accepts: String
	 * Returns: TTTRecord
	 * Description: Returns a TTTRecord with a matching configuration as the 
	 * 				one provided through the argument. Does this by getting the
	 * 				head of a LinkedList associated with a hash index, and searching
	 * 				through the LinkedList for the TTTRecord with the corresponding
	 * 				configuration
	 */
	public TTTRecord get (String config){
		int dictionary_index = getHashIndex(config);
		LinkedList record_node = bucket.get(dictionary_index);//will have null value if empty
		if(record_node != null){
			while(record_node!=null){
				TTTRecord head = record_node.getRecord();
				if(head.getConfiguration().equals(config)){
					return head;
				}
				record_node = record_node.getNext();
			}
		}
		
		return null;	
	}
	
	/*
	 * Method: numElements
	 * Accepts: N/A
	 * Returns: int
	 * Description: Returns the number of records in the dictionary 
	 */
	public int numElements(){
		return number_of_records;
	}
	
	/*
	 * Method: TTTDictionary
	 * Accepts: int
	 * Returns: N/A
	 * Description: Constructor, fills dictionary with null values, and
	 * 				initializes variables. 
	 */
	public TTTDictionary(int size){
		this.size = size;
		number_of_records = 0;
		bucket = new ArrayList<LinkedList>();
		for(int i = 0; i < size; i++)
			bucket.add(null);
	}
	
	/*
	 * Method: getHashIndex
	 * Accepts: String
	 * Returns: int
	 * Description: creates a hash index which will be used to store values
	 * 				in the dictionary 
	 */
	private int getHashIndex(String config){
		int hash = 0;
		for (int i = 0; i < config.length(); i++){
			hash = (37* hash + config.charAt(i)) % this.size;
		}
		return hash;
	}
}
