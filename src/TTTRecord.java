
public class TTTRecord {
	private String config;
	private int score;
	private int level;
	
	/*
	 * Method: TTTRecord
	 * Accepts: String, int, int
	 * Returns: N/A
	 * Description: Constructor, initializes variables 
	 */
	public TTTRecord(String config, int score, int level){
		this.config = config;
		this.score = score;
		this.level = level;
	}
	
	/*
	 * Method: getConfiguration
	 * Accepts: N/A
	 * Returns: String
	 * Description: returns a TTTRecords associated configuration
	 */
	public String getConfiguration(){
		return this.config;
	}
	
	/*
	 * Method: getScore
	 * Accepts: N/A
	 * Returns: int
	 * Description: returns a TTTRecords associated score 
	 */
	public int getScore(){
		return this.score;
	}
	
	/*
	 * Method: getLevel
	 * Accepts: N/A
	 * Returns: int
	 * Description: returns a TTTRecords associated level
	 */
	public int getLevel(){
		return this.level;
	}
}
