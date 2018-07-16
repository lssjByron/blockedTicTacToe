
public class BlockedTicTacToe {
	private int board_size;
	private int inline;
	private int max_levels;
	private boolean is_winner;
	char[][] gameBoard;
	
	/*
	 * Method: BlockedTicTacToe
	 * Accepts: int, int, int
	 * Returns: N/A
	 * Description: Constructor, initializes variables, and fills
	 * 				2d char array gameboard with empty spaces 
	 */
	public BlockedTicTacToe (int board_size, int inline, int max_levels){
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size];
		//Initialize gameboard
		for (int row = 0; row < board_size; row ++)
		    for (int col = 0; col < board_size; col++)
		        this.gameBoard[row][col] = ' ';
		}
	
	/*
	 * Method: createDictionary
	 * Accepts: N/A
	 * Returns: TTTDictionary
	 * Description: creates a new array of the size 4000 and returns it
	 */
	public TTTDictionary createDictionary() {
		TTTDictionary dict = new TTTDictionary(4000);
		return dict;
	}
	
	/*
	 * Method: repeatedConfig
	 * Accepts: TTTDictionary
	 * Returns: int
	 * Description: gameBoard is represented as a string then
	 * 				checks if gameBoard is in the configurations
	 * 				dictionary. If it is in the in the dictionary
	 * 				the method returns its associated score, 
	 * 				otherwise it returns -1
	 */
	public int repeatedConfig(TTTDictionary configurations){
		String temp_config = convertToConfig();
		TTTRecord rec = configurations.get(temp_config);
		if(rec == null)
			return -1;
		else
			return rec.getScore();
	}
	
	/*
	 * Method: insertConfig
	 * Accepts: TTTDictionary, int, int
	 * Returns: N/A
	 * Description:  Creates a new TTTRecord and places it into 
	 * 				 the dictionary provided in the arguments.
	 * 				 If the dictionary already contains the record
	 * 				 an error is printed to the user
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level){
		String temp_config = convertToConfig();
		TTTRecord rec = new TTTRecord(temp_config, score, level);
		try {
			configurations.put(rec);
		} catch (DuplicatedKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Method: storePlay
	 * Accepts: int, int, char
	 * Returns: N/A
	 * Description: stores a symbol provided as an argument into
	 * 				the 2d char array gameBoard 
	 */
	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col] = symbol;
	}
	
	/*
	 * Method: squareIsEmpty
	 * Accepts: int, int
	 * Returns: boolean
	 * Description: Check if gameboard is empty at the location
	 * 				row and column, if it is return true, else 
	 * 				return false
	 */
	public boolean squareIsEmpty (int row, int col){
		if(this.gameBoard[row][col] == ' ')
			return true;
		else
			return false;
	}
	
	/*
	 * Method: convertToConfig
	 * Accepts: N/A
	 * Returns: String
	 * Description: Returns a String value of gameBoard
	 */
	private String convertToConfig(){
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < board_size; row++)
		    for (int col = 0; col < board_size; col++)
		        builder.append(this.gameBoard[row][col]);
		return builder.toString();
	}

	/*
	 * Method: checkWins
	 * Accepts: int, int, String, char, String
	 * Returns: boolean
	 * Description: checkWins uses the spaces between symbols where matches will be found (difference),
	 * 			    the last possible point where a symbol will be found to make a match (last_check),
	 * 				a string containing the current representation of gameBoard to check for matches (temp_config),
	 * 				the symbol being checked (symbol), and the type of check ie. diagnol, horizontal... 
	 * 				(type_of_check) to determine if there is a winner 
	 */
	private boolean checkWins(int difference, int last_check, String temp_config, char symbol, String type_of_check){
		int[] check_symbols = new int[this.inline];
		for(int i = 0; i < temp_config.length();i++){
			if(temp_config.charAt(i) == symbol){
				//right to left diagonal can't start with a value(x,o) on the left most side of the board
				if((type_of_check.equals("right_left_diagonal")) && (i % this.board_size) == 0)
					continue;
				//array stores 0 when matches are not found
				for(int c = 0; c < this.inline - 1; c++)
					check_symbols[c] = 0;
				int symbol_counter = 0;
				check_symbols[symbol_counter] = 1;
				
				//last_check signifies where the last symbol for a match will be, if its value is
				//greater than temp_config then the last match is past the gameboards length and 
				//thus there is no match
				if((last_check+i) < temp_config.length()){
					//use difference to determine where the next match will be
					for(int c = i + difference; c <= (last_check+i); c+=difference ){
						if((type_of_check.equals("horizontal")||type_of_check.equals("left_right_diagonal")) && (c % this.board_size) == 0)
							break;
						if(temp_config.charAt(c) == symbol){ //match found
							symbol_counter++;
							check_symbols[symbol_counter] = 1;
						}
						else //match not found
							break;
						
						//right to left diagonal can't start with a value(x,o) on the left most side of the board
						if(type_of_check.equals("right_left_diagonal") && (c % this.board_size) == 0)
							break;
						
						//winner has been determined
						if(check_symbols[this.inline -1] == 1)
			        		break;
					}
				}
				if(check_symbols[this.inline -1] == 1)
	        		break;
			}
		}
		if(check_symbols[this.inline -1] == 1)
    		return true;
		else
			return false;
	}
	
	/*
	 * Method: wins
	 * Accepts: symbol
	 * Returns: boolean
	 * Description: checks if a winner has been found, if one has been found it returns
	 * 				true, otherwise it returns false
	 */
	public boolean wins (char symbol){
		String temp_config = convertToConfig();
		//check for top right to bottom left diagonals
		is_winner = checkWins(this.board_size -1, (this.inline - 1) *(this.board_size - 1), temp_config, symbol, "right_left_diagonal");
		if(is_winner)
			return true;
		
		//*check for top left to bottom right diagonals
		is_winner = checkWins(this.board_size + 1, (this.inline-1) * (this.board_size+1), temp_config, symbol, "left_right_diagonal");
		if(is_winner)
			return true;
		
		//straight line (up to down)
		is_winner = checkWins(this.board_size, this.board_size*(this.inline-1), temp_config, symbol, "vertical");
		if(is_winner)
			return true;
		
		//straight line (left to right)
		is_winner = checkWins(1, this.inline-1, temp_config, symbol, "horizontal");
		if(is_winner)
			return true;
		is_winner = false;
		return is_winner;
	}
	
	/*
	 * Method: isDraw
	 * Accepts: N/A
	 * Returns: boolean
	 * Description: first iterates through gameBoard to determine if there are any spaces,
	 * 				if there are no spaces found in gameBoard and there is no winner
	 * 				then the game is a draw and the method returns true, otherwise it
	 * 				returns false 
	 */
	public boolean isDraw(){
		boolean is_empty = true;
		for (int row = 0; row < this.board_size; row ++)
		    for (int col = 0; col < this.board_size; col++)
		        if(this.gameBoard[row][col] == (char) (' '))
		        	is_empty = false; 
		if(is_empty == false || is_winner == true)
			return false;
		else 
			return true;
	}
	
	/*
	 * Method: evalBoard
	 * Accepts: N/A
	 * Returns: int
	 * Description: Returns a 0 if a human wins, a 3 if a computer wins,
	 * 				a 2 if the game is ongoing, and a 1 if the game is a
	 * 				draw
	 */
	public int evalBoard(){
		boolean is_human_winner = wins('x');
		if(is_human_winner)
			return 0;
		boolean is_computer_winner = wins('o');
		if(is_computer_winner)
			return 3;
		String config = convertToConfig();
		if(config.contains(" "))
			return 2;
		return 1;
	}
}
	
