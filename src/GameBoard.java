import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * This class represents the GUI in which users can play TicTacToe. 
 * 
 * You do NOT need to change anything in this class.
 * 
 */
public class GameBoard implements ActionListener
{
	//Instance variables to make the GUI frame
	private JFrame frame;
	private JPanel panel;
	private JLabel topLabel;
	private JButton[][] buttons;
	
	public static final int BOARD_SIZE = 3;
	
	//Instance variables to keep track of game play
	private String currentPlayer;
	private TicTacToeGame game;
	private boolean isGameOver;
	
	public GameBoard()
	{
		init();
	}
	
	private void init()
	{
		//Setup GUI
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		//Setup first player as X
		currentPlayer = TicTacToeGame.PLAYER_X;

		//Start new TicTacToeGame
		game = new TicTacToeGame();
		isGameOver = false;

		//Set waiting message for top of GameBoard
		topLabel = new JLabel( getWaitingMessage() );
		panel.add(topLabel,BorderLayout.NORTH);

		//Add buttons to GUI
		setupButtons( BOARD_SIZE );

		//Add component to content pane and then show it!
		frame.getContentPane().add(panel); 
		frame.pack();
		frame.setVisible( true );

	}

	/**
	 * Determines waiting message for top of GUI based on current player 
	 * 
	 * Preconditions: None
	 * 
	 */
	private String getWaitingMessage()
	{
		if( currentPlayer.equals( TicTacToeGame.PLAYER_X ) )
		{
			return "Player X it's your turn...";
		}
		else
		{
			return "Player O it's your turn...";
		}
	}

	/**
	 * Switches the current player from X to O, or vice versa.
	 * 
	 * Preconditions: None
	 * Postconditions: Updates instance variable currentPlayer 
	 * 
	 */
	private void switchCurrentPlayer()
	{
		if( currentPlayer.equals( TicTacToeGame.PLAYER_X ) )
		{
			currentPlayer = TicTacToeGame.PLAYER_O;
			
			//IF YOU'RE DOING THE EXTRA CREDIT, UNCOMMENT THIS SECTION!
			
			//Get computer generated move
			//Location computerMove = game.getComputerMove();
			//int row = computerMove.getRow();
			//int col = computerMove.getColumn();
			//updateButtons( new Location(row, col) );
			
			//Switch player back to X
			//switchCurrentPlayer();
		}
		else
		{
			currentPlayer = TicTacToeGame.PLAYER_X;
		}
	}

	/**
	 * Does initial setup for buttons. Creates a 2D grid of buttons (size by size).
	 * Sets each button with an empty string as text
	 * 
	 * @param size - number of buttons in each row and each column
	 * 				If size is 3, then a 3x3 grid of buttons is created
	 * 
	 * Preconditions: size > 0
	 * Postconditions: buttons[][] is defined and the JButton objects within it are also defined
	 * 
	 */
	private void setupButtons(int size) 
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(size,size));
		buttons = new JButton[size][size];
		for (int i=0;i<size;i++)
		{
			for (int j=0;j<size;j++) 
			{
				buttons[i][j] = new JButton("              ");
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
			}
		}
		panel.add(buttonPanel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Update button at (row, column) for the currentPlayer
	 * 
	 * @param row
	 * @param column
	 * 
	 * Preconditions: The button at (row, column) is not already occupied and is a valid location on the grid
	 * Postconditions: The rest of the buttons[][] stays the same, only the button at the given location is updated 
	 * 					with the appropriate player (i.e. the currentPlayer)
	 * 
	 */
	private void updateButtons( Location move )
	{
		int row = move.getRow();
		int column = move.getColumn();
		buttons[row][column].setText( "      " + currentPlayer + "        " );
	}

	/**
	 * Captures the actionPerformed, likely a button push on the grid
	 * Updates the GUI board and the TicTacToeGame object 
	 * 
	 * Preconditions: None
	 * Postconditions: Board is updated based on move determined by button push
	 * 					game object is also informed of this move so it can check for a winner, if there's one yet
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		//If game is already over but GUI is still open, do NOT allow user to update other buttons
		if( isGameOver )
		{
			return;
		}
		
		System.out.println("Got a button push");
		
		//Find the button that the user pressed by looking at every button
		for (int i=0;i<buttons.length;i++)
		{
			for (int j=0;j<buttons.length;j++)
			{
				//Check if its the button at (i,j)
				if (event.getSource() == buttons[i][j]) 
				{
					//Yes! We've found the right button
					System.out.println("Got button at "+ i +" " + j);
					
					//Check if that location is already occupied or if someone can move there
					boolean isValidMove = game.isValidMove( new Location(i,j) );
			
					if( isValidMove )
					{
						//It's a valid move! 
						
						//First update the buttons on the GUI so the new move is displayed for the currentPlayer
						updateButtons( new Location(i,j) );
						//Then update the game object, where the real game play action happens
						game.move( new Location(i,j), currentPlayer );

						//Was that a winning move?!?! Let's check!
						String winner = game.getWinner();
						if( winner.equals( TicTacToeGame.NO_WINNER ) )
						{
							//No winner yet... let's keep playing: First switch the currentPlayer to the next player
							switchCurrentPlayer();
							//Update the text at the top of the GUI to reflect that it's the new player's turn
							topLabel.setText( getWaitingMessage() );	
						}
						else
						{
							//We have a winner! The game is over
							isGameOver = true;
							
							//Print the winner for the user - The first line appears in the GUI, the second line in the Java console
							topLabel.setText( "Game over! The winner is: " + winner );
							System.out.println( "Game over! The winner is: " + winner );
						}
						
					}
					else
					{
						//The button pressed does not reflect a valid move so don't switch currentPlayer. Let the user pick another button
						topLabel.setText( "Not a valid move. Try again " + currentPlayer);
						break;
					}
				}	
			}
		}

	}


}
