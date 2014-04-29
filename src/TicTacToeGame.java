
/**
 * 
 * This class represents the TicTacToe game itself - how valid moves are made and 
 * 		how the winner is determined
 * 
 * YOUR TASK: Implement the constructor method, as well as columnWin, rowWin, and diagonalWin
 * 
 */
public class TicTacToeGame 
{
	public static final String PLAYER_X = "X";
	public static final String PLAYER_O = "O";
	public static final String NO_WINNER = "NO_WINNER";

	private final int SIZE = GameBoard.BOARD_SIZE;

	public String[][] board;

	/**
	 * Creates an empty board, which is represented by a 3x3 array and stored in an instance variable.
	 */
	public TicTacToeGame()
	{
		//TODO: INSERT YOUR CODE HERE
		board = new String [3][3];// This is a 2D square array
		//This for loop changes the value from null to a empty string.
        for (int r=0; r<board.length;r++)
        {
      	  for (int c=0; c<board[r].length;c++)
      	  {
      		  board[r][c]="";
      	  }
        }


	}

	/**
	 * Checks if the given position (row, column) in the grid is already occupied.
	 * Return true if not already occupied; o/w false.
	 * 
	 * @param row
	 * @param column
	 * @param player - PLAYER_X or PLAYER_O
	 * 
	 * Preconditions: Row & column are within (size-1) of board
	 * 
	 */
	public boolean isValidMove( Location move )
	{
		int row = move.getRow();
		int column = move.getColumn();
		
		String currentValue = board[row][column];
		//Move is valid if it is not occupied by player X or player O
		boolean isValid = !( currentValue.equals( PLAYER_X ) || currentValue.equals( PLAYER_O ) );	

		return isValid;
	}

	/**
	 * Sets the position (row, column) for the given player. Update the your local copy of the game board.
	 * Check if the move is valid before assigning the given player to this position.
	 * 
	 * @param row
	 * @param column
	 * @param player - PLAYER_X or PLAYER_O
	 * 
	 * Preconditions: player is PLAYER_X or PLAYER_O
	 * 
	 */
	public void move( Location move, String player)
	{
		int row = move.getRow();
		int column = move.getColumn();
		
		if( isValidMove( move ) )
		{
			board[row][column] = player;
		}
	}
	
	/**
	 * Generates a next move for PLAYER_O to an unoccupied location. Should be strategic.
	 * 
	 * @return Location corresponding to intended valid move
	 */
	public Location getComputerMove()
	{
		//INSERT YOUR CODE HERE -- EXTRA CREDIT. 
		
		//If you're doing this, uncomment the code in Game Board method switchCurrentPlayer()
		
		return new Location(0,0);
	}
	

	/**
	 * Returns an PLAYER_X or PLAYER_O to indicate a winner, or NO_WINNER if there is no winner yet. 
	 * Remember that a winning position has three matching marks in a row, column, or diagonal. 
	 * 
	 * NOTE: It might be helpful to create other helper methods that check for each way to win 
	 * (i.e a method to check for a win along a row, a method to check for a win along a column, 
	 * 		and another method for a win along a diagonal). And then call those methods 
	 * 		from within this method
	 * 
	 * @return - PLAYER_X or PLAYER_O if there's a winner, otherwise NO_WINNER
	 * 
	 * Preconditions: None
	 * 
	 */
	public String getWinner()

	{
		if( columnWin().equals(PLAYER_X) || rowWin().equals(PLAYER_X) || diagonalWin().equals(PLAYER_X) )
		{
			return PLAYER_X;
		}
		else if( columnWin().equals(PLAYER_O) || rowWin().equals(PLAYER_O) || diagonalWin().equals(PLAYER_O) )
		{
			return PLAYER_O;
		}
		else
		{
			return NO_WINNER;
		}
	}

	/**
	 * Checks if there is a win along any of the columns
	 * 
	 * @return PLAYER_X if x has won along a column, PLAYER_O if o has won along a column; o/w returns NO_WINNER
	 */
	public String columnWin()
	{
		//Keeps track of which player has been found to be the winner
		String columnWinner = NO_WINNER;

		//TODO: INSERT YOUR CODE HERE
		
		
			for(int c=0; c<board[0].length; c++)
			{
				String expected=board[0][c];//this variable will always be in row 1 because that's where the column the starts!!
				if(expected.equals(board[1][c])&& expected.equals(board[2][c]))
				{
					if (expected.equals(PLAYER_X)|| expected.equals(PLAYER_O))
						// you have to check this because the first square might just be a empty string so that's why the 
						//first row or column doesn't work
					{
						columnWinner=expected;
					}
				}
			}
	
		return columnWinner;
	}

	/**
	 * Checks if there is a win along any of the rows
	 * 
	 * @return PLAYER_X if x has won along a row, PLAYER_O if o has won along a row; o/w returns NO_WINNER
	 */
	public String rowWin()// 
	{
		//Keeps track of which player has been found to be the winner
		String rowWinner = NO_WINNER;

		//TODO: INSERT YOUR CODE HERE		
		for(int r=0; r<board.length; r++)
		{
			String expected= board[r][0];// the expected value is always in column 1
			
				if (expected.equals(board[r][1])&& expected.equals(board[r][2]))// checks if it's row Win!!!
				{
					if (expected.equals(PLAYER_X)||expected.equals(PLAYER_O))
						// you have to check this because the first square might just be a empty string so that's why the 
						//first row or column doesn't work
					{
						rowWinner=expected;
					}
				}
				
		}
		return rowWinner;
	}

	/**
	 * Checks if there is a win along any of the diagonals
	 * 
	 * @return PLAYER_X if x has won along a diagonal, PLAYER_O if o has won along a diagonal; o/w returns NO_WINNER
	 */
	public String diagonalWin()
	{
		//Keeps track of which player has been found to be the winner
		String diagonalWinner = NO_WINNER;

		//TODO: INSERT YOUR CODE HERE
		for (int r=0; r<board.length;r++)
		{ 
			String expectedCase1=board[0][0];
			String expectedCase2=board[0][2];
			if (expectedCase1.equals(board[1][1])&& expectedCase1.equals(board[2][2]))
			{
				diagonalWinner=expectedCase1;
			}
			if (expectedCase2.equals(board[1][1])&& expectedCase2.equals(board[2][0]))
			{
				diagonalWinner=expectedCase2;
			}
			
		}

		return diagonalWinner;
	}

	public static void main(String[] args) 
	{
		//Display GUI
		new GameBoard();
	}

}
