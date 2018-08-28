package game;

import java.util.Scanner;

public class GameMain
{
	public static void main(String[] args)
	{
		new GameMain();
	}
	
	/* private int numPlayers;         <future implementation for AI opponents> */
	/**
	 * the main board state. Numbers represent labels for each space. Players type the number corresponding to the space they wish to play on.
	 */
	private String[][] gameState =  {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
	/**
	 * Internal monitor for which spaces are used by players. "0" if the space is available for play, "1" if the space is filled by a player's marker.
	 */
	private int[][] openSpaces = new int[3][3];
	/**
	 * Internal monitor for which player's turn it is. "0" for player 1, "1" for player 2.
	 */
	private int turnSwitch = 0;
	/**
	 * Scanner utility for reading players' inputs.
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * Internal monitor for checking whether there is a winner. "0" if no player has won the game, "1" if player 1 has won, "2" if player 2 has won.
	 */
	private int winner = 0;
	
	/**
	 * Constructor for the game. Prints the initial board state and starts the game beginning with player 1.
	 */
	public GameMain()
	{
		// gameStart();
		printBoard();
		turn();
	}
	
/* <<<<<<<< future implementation for AI opponents >>>>>>>>
/*	
	private void gameStart()
	{
		String n;
		System.out.println("Input Number of Players (0/1/2): ");
		n = sc.nextLine();
		if (n.equals("0") || n.equals("1") || n.equals("2"))
		{
			numPlayers = Integer.parseInt(n);
			System.out.println("Set number of players: " + numPlayers);
		}
		else
		{
			System.out.println("Number of players must be 0, 1, or 2.");
			gameStart();
		}
	}
*/
	
	/**
	 * Prints the current state of the board in the style of a traditional tic-tac-toe board.
	 */
	private void printBoard()
	{
		System.out.println(" " + gameState[0][0] + " | " + gameState[0][1] + " | " + gameState[0][2] );
		System.out.println("-----------");
		System.out.println(" " + gameState[1][0] + " | " + gameState[1][1] + " | " + gameState[1][2] );
		System.out.println("-----------");
		System.out.println(" " + gameState[2][0] + " | " + gameState[2][1] + " | " + gameState[2][2] );
	}
	
	/**
	 * Updates the board corresponding with the player's turn.
	 * @param p The current player
	 * @param r The row of the player's selected space
	 * @param c The column of the player's selected space
	 */
	private void updateBoard(int p, int r, int c)
	{
		if (openSpaces[r][c] == 0)
		{
			if(p == 0)
			{
				gameState[r][c] = "X";
				turnSwitch = 1;
			}
			else
			{
				gameState[r][c] = "O";
				turnSwitch = 0;
			}
			openSpaces[r][c] = 1;
			checkWin();
		}
		else
		{
			System.out.println("That space is not available.");
		}
		
		printBoard();
		if (winner == 1) System.out.println("Player 1 wins!");
		else if (winner == 2) System.out.println("Player 2 wins!");
		else if (checkDraw()) System.out.println("Draw! Nobody Wins!");
		else turn();
	}

/**
 * The central method of the game. Scans the current player's input, then sends instructions to update the board.
 */
	private void turn()
	{
		if (turnSwitch == 0)
			System.out.println("It is player 1's turn. Choose a space:");
		else
			System.out.println("It is player 2's turn. Choose a space:");
		String input = sc.nextLine();
		if (input.equals("1")) updateBoard(turnSwitch, 0, 0);
		else if (input.equals("2")) updateBoard(turnSwitch, 0, 1);
		else if (input.equals("3")) updateBoard(turnSwitch, 0, 2);
		else if (input.equals("4")) updateBoard(turnSwitch, 1, 0);
		else if (input.equals("5")) updateBoard(turnSwitch, 1, 1);
		else if (input.equals("6")) updateBoard(turnSwitch, 1, 2);
		else if (input.equals("7")) updateBoard(turnSwitch, 2, 0);
		else if (input.equals("8")) updateBoard(turnSwitch, 2, 1);
		else if (input.equals("9")) updateBoard(turnSwitch, 2, 2);
		else
		{
			System.out.println("Invalid entry. Input number corresponding to desired space.");
			turn();
		}
	}

/**
 * Scans all possible win states for each player. A win occurs when a player has aligned 3 of their markers in one row, one column, or diagonally.
 */
	private void checkWin()
	{
		if(gameState[0][0].equals("X") && gameState[0][1].equals("X") && gameState[0][2].equals("X")) winner = 1;
		else if(gameState[1][0].equals("X") && gameState[1][1].equals("X") && gameState[1][2].equals("X")) winner = 1;
		else if(gameState[2][0].equals("X") && gameState[2][1].equals("X") && gameState[2][2].equals("X")) winner = 1;
		else if(gameState[0][0].equals("X") && gameState[1][0].equals("X") && gameState[2][0].equals("X")) winner = 1;
		else if(gameState[0][1].equals("X") && gameState[1][1].equals("X") && gameState[2][1].equals("X")) winner = 1;
		else if(gameState[0][2].equals("X") && gameState[1][2].equals("X") && gameState[2][2].equals("X")) winner = 1;
		else if(gameState[0][0].equals("X") && gameState[1][1].equals("X") && gameState[2][2].equals("X")) winner = 1;
		else if(gameState[2][0].equals("X") && gameState[1][1].equals("X") && gameState[0][2].equals("X")) winner = 1;
		else if(gameState[0][0].equals("O") && gameState[0][1].equals("O") && gameState[0][2].equals("O")) winner = 2;
		else if(gameState[1][0].equals("O") && gameState[1][1].equals("O") && gameState[1][2].equals("O")) winner = 2;
		else if(gameState[2][0].equals("O") && gameState[2][1].equals("O") && gameState[2][2].equals("O")) winner = 2;
		else if(gameState[0][0].equals("O") && gameState[1][0].equals("O") && gameState[2][0].equals("O")) winner = 2;
		else if(gameState[0][1].equals("O") && gameState[1][1].equals("O") && gameState[2][1].equals("O")) winner = 2;
		else if(gameState[0][2].equals("O") && gameState[1][2].equals("O") && gameState[2][2].equals("O")) winner = 2;
		else if(gameState[0][0].equals("O") && gameState[1][1].equals("O") && gameState[2][2].equals("O")) winner = 2;
		else if(gameState[2][0].equals("O") && gameState[1][1].equals("O") && gameState[0][2].equals("O")) winner = 2;
	}

/**
 * Scans the board for available spaces for play. If there are no more available spaces and neither player has won, the game ends in a draw.
 * @return whether the game has resulted in a draw
 */
	private boolean checkDraw()
	{
		for (int r = 0 ; r < 3 ; r++)
		{
			for (int c = 0 ; c < 3; c++)
			{
				if (openSpaces[r][c] == 0) return false;
			}
		}
		return true;
	}
}
