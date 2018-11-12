package sodukusolver;

import java.util.concurrent.TimeUnit;

/**
 * An automated process for solving a Sudoku puzzle of any size square grid using a backtracking algorithm.
 * @author Sam Henley
 *
 */
public class SodukuSolver
{
	/**
	 * Current state of the puzzle grid
	 */
	private int[][] grid;
	/**
	 * Times how long the algorithm takes to solve the puzzle
	 */
	private Stopwatch timer;
	/**
	 * length of one side of the puzzle grid
	 */
	private int size;
	
	/**
	 * Initializes data fields and ensures the input grid is a valid Sudoku puzzle grid.
	 * A Sudoku puzzle grid is valid if: A) the lengths of all sides are equal, and
	 * B) All numbers in the grid are {@code 0 < m < s}, where {@code m} is a number, and
	 * {@code s} is the length of on side of the grid (0's represent unassigned values).
	 * @param grid the Sudoku puzzle to solve
	 */
	public SodukuSolver(int[][] grid)
	{
		if (grid.length != grid[0].length) throw new InvalidGridException("Sudoku grid must be a square!");
		
		for (int r = 0 ; r < grid.length ; r++)
		{
			for (int c = 0 ; c < grid.length ; c++)
			{
				if (grid[r][c] > grid.length) throw new InvalidGridException("Sudoku grid contains an invalid number!");
			}
		}
		
		this.grid = grid;
		size = grid.length;
		timer = new Stopwatch();
		
	}
	
	/**
	 * Initiates solving of puzzle
	 */
	public void start()
	{
		timer.start();
		if (solve()) finished();
		else
			System.out.println("A solution for the given puzzle could not be found!");
	}
	
	/**
	 * Recursively solves the puzzle by checking if a certain value is valid in a square, then continues to solve
	 * assuming that value is correct. A value is valid if that value is not present in that square's row, column,
	 * or box (the box is {@code sqrt(size)}, e.g. in a 9x9 puzzle, the box is 3x3). If the value turns out to be
	 * invalid, the next possible value is stored and solving continues.
	 * @return true if the solve was successful, false otherwise
	 */
	private boolean solve()
	{
		if (findUnassignedCell() == null) return true;
		
		int[] cell = findUnassignedCell();
		
		
		for (int n = 1; n <= size ; n++)
		{
			if (isSafe(cell[0], cell[1], n))
			{
				grid[cell[0]][cell[1]] = n;
				if(solve()) return true;
				else
					{
					grid[cell[0]][cell[1]] = 0;
					continue;
					}
			}
		}
		
		return false;
	}
	
	/**
	 * Tests if a value is valid in a certain square. See solve() for the definition of valid.
	 * @param r the row of the square to check
	 * @param c the column of a square to check
	 * @param trial the value to try
	 * @return true if the value is valid, false otherwise
	 */
	private boolean isSafe(int r, int c, int trial)
	{
		int sqrt = (int) Math.sqrt(size);
		int tlcR = r - r % sqrt;
		int tlcC = c - c % sqrt;
		
		
		for (int row = tlcR ; row < tlcR + sqrt ; row++)
		{
			for (int col = tlcC; col < tlcC + sqrt ; col++)
				if (grid[row][col] == trial) return false;
		}
		
		for (int row = 0 ; row < size ; row++)
			if (grid[row][c] == trial) return false;
		
		for (int col = 0 ; col < size ; col++)
			if (grid[r][col] == trial) return false;
		
		return true;

		}
	
	/**
	 * Searches the grid for an unassigned value (if the value of the square == 0)
	 * @return [row, column] of an unassigned square
	 */
	private int[] findUnassignedCell()
	{
		for (int row = 0 ; row < size ; row++)
		{
			for (int col = 0; col < size ; col++)
				if (grid[row][col] == 0) return new int[] {row,col};
		}
		return null;
	}
	
	/**
	 * Prints the grid to the console.
	 */
	private void printGrid()
	{
		for (int row = 0 ; row < size ; row++)
		{
			String str = "";
			for (int col = 0; col < size ; col++)
			{
				str = str + grid[row][col] + " ";
			}
			System.out.println(str);
		}
	}
	
	/**
	 * Runs when the algorithm has finished solving the puzzle.
	 */
	private void finished()
	{
		timer.stop();
		System.out.println("Puzzle solved in " + TimeUnit.SECONDS.convert(timer.getElapsedTime(), TimeUnit.NANOSECONDS) + " seconds!" );
		printGrid();
	}
		
	/**
	 * Driver method where the grid is input and the solver is started.
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[][] grid = new int[][] 
			    { 
	            {4, 0, 0, 0, 0, 3, 0, 0, 0},
	            {0, 0, 5, 0, 9, 0, 0, 3, 0}, 
	            {3, 0, 0, 0, 0, 0, 5, 4, 0}, 
	            {2, 0, 0, 0, 6, 0, 0, 5, 7}, 
	            {0, 7, 0, 0, 8, 0, 3, 0, 0}, 
	            {6, 1, 0, 0, 0, 0, 0, 0, 0}, 
	            {0, 0, 0, 9, 0, 6, 0, 0, 5}, 
	            {9, 0, 0, 1, 5, 8, 0, 6, 0}, 
	            {1, 0, 0, 0, 0, 0, 9, 2, 0}
			    }; 
									
		SodukuSolver s = new SodukuSolver(grid);
		s.start();
	}
}
