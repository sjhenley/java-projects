package sodukusolver;

import java.util.concurrent.TimeUnit;

public class SodukuSolver
{
	private int[][] grid;
	private Stopwatch timer;
	private int size;
	
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
	
	public void start()
	{
		timer.start();
		if (solve()) finished();
		else
			System.out.println("ERROR: puzzle not solved!");
	}
	
	public boolean solve()
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
	
	public int[] findUnassignedCell()
	{
		for (int row = 0 ; row < size ; row++)
		{
			for (int col = 0; col < size ; col++)
				if (grid[row][col] == 0) return new int[] {row,col};
		}
		return null;
	}
	
	public void printGrid()
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
	
	private void finished()
	{
		timer.stop();
		System.out.println("Puzzle solved in " + TimeUnit.SECONDS.convert(timer.getElapsedTime(), TimeUnit.NANOSECONDS) + " seconds!" );
		printGrid();
	}
		
	
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
