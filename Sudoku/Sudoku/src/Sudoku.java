/*Guillermo Alicea
 * COP 3503C - 0013
 * Extra Credit - Sudoku Solver
 * 03/14/16
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku extends Applet
{
	private static final long serialVersionUID = 1L;
	final static int N = 9;
	BufferedImage m_objShape;
	MediaTracker tracker = new MediaTracker(this);
	static final int NUMROWS = 9; 
	static final int NUMCOLS = 9; 
	static final int SQUAREWIDTH = 40; 
	static final int SQUAREHEIGHT = 40;
	static final int BOARDLEFT = 40; 
	static final int BOARDTOP = 40; 
	static int m_nBoard[][] = new int[9][9];
	static boolean check = true;
	static double startTime = System.nanoTime();
	static int solutionFound = 0;
	
	public void init()
	{
        setSize(1020,700);
        
        try 
        {
        	LoadData();
		} 
        catch (Exception e1) 
        {
		}
        
	}
	
	void DrawSquares( Graphics canvas )
	{  
		canvas.setColor(Color.BLACK); 
		
		//delay
		try
		{
			Thread.sleep(250);
		}
		catch(Exception el){}
		
		for( int nRow=0; nRow<NUMROWS; nRow++ )  
		{   
			for( int nCol=0; nCol<NUMCOLS; nCol++ )   
			{   
				canvas.clearRect(BOARDLEFT + nCol * SQUAREWIDTH,     
						BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
				canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,     
						BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
				
				canvas.drawString(String.valueOf(Character.forDigit(m_nBoard[nRow][nCol], 10)), BOARDLEFT + nCol * SQUAREWIDTH + 17,
						BOARDTOP + nRow * SQUAREHEIGHT + 27);
			} 
		}
		
		canvas.clearRect(BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 50, 1000, 1000);
		
		//Solve once and only once. Display whether a solution was found or not
		if(check)
		{
			check = false;
			if(SolveSudoku(canvas))
				solutionFound = 1;
			else
				solutionFound = -1;
			paint(canvas);
		}
	}
	
	//Display everything
	public void paint(Graphics canvas)
	{		
		DrawSquares(canvas);
		canvas.drawString("SUDOKU SOLVER",
				BOARDLEFT, 30);
		canvas.setColor(Color.BLUE);
		canvas.drawString("Blue Box = Working Index",
				BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 20);
		canvas.setColor(Color.RED);
		canvas.drawString("Red Box = Conflicting Index",
				BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 40);
		canvas.setColor(Color.BLACK);
		if(solutionFound == 0)
			canvas.drawString("Finding Solution...",
					BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 60);
		else if(solutionFound == -1)
			canvas.drawString("Solution does not exist.",
					BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 60);
		else
			canvas.drawString("Solution Found!",
					BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 60);
		canvas.drawString(String.valueOf("Elasped Time: " + String.valueOf((int)((System.nanoTime() - startTime)/1000000000)/60))+"m, "+
					String.valueOf((int)((System.nanoTime() - startTime)/1000000000)%60)+"s",
				BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 9 + 80);
	}
	
	static void LoadData() throws FileNotFoundException
	{
		// Open the file for reading. Will almost always be input.txt
		Scanner objScanner = new Scanner(new File("input.txt"));
		
		// Iterate through the rows.
		for( int nRow=0; nRow<N; nRow++ )
		{
			// Iterate through the columns.
			for( int nColumn=0; nColumn<N; nColumn++ )
			{
				// Read each value. Note that this is [row][col] but could be [col][row] in
				//   other contexts.
				m_nBoard[nRow][nColumn] = objScanner.nextInt();
			}
		}
		// Closing is good practice and avoids an eclipse warning.
		objScanner.close();
	}
	
	/* Searches the grid to find an entry that is still unassigned. If
	   found, the reference parameters row, col will be set the location
	   that is unassigned, and true is returned. If no unassigned entries
	   remain, false is returned. */
	static int FindUnassignedLocation( int grid[][] )
	{
		  for( int row=0; row<N; row++ )
		  {
		    for( int col=0; col<N; col++ )
		    {
		      if( grid[row][col] == 0 )
		      {
		        return( col | ( row << 8 ) );
		      }
		    }
		  }

		return( -1 );
	}
	
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified row matches the given number. */
	static int UsedInRow(int grid[][], int row, int num)
	{
		for( int col=0; col<9; col++ )
		{
			if( grid[row][col] == num ) return col;
		}
		
		return -1;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified column matches the given number. */
	static int UsedInCol(int grid[][], int col, int num)
	{
		for( int row=0; row<9; row++ )
		{
			if( grid[row][col] == num ) return row;
		}

		return -1;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   within the specified 3x3 box matches the given number. */
	static int UsedInBox( int grid[][], int boxStartRow, int boxStartCol, int num )
	{
		for( int row=boxStartRow; row<boxStartRow+3; row++ )
		{
			for( int col=boxStartCol; col<boxStartCol+3; col++ )
			{
				if( grid[row][col] == num ) return (col | ( row << 8));
			}
		}
		
		return -1;
	}
	 
	/* Returns a boolean which indicates whether it will be legal to assign
	   num to the given row,col location. */
	int IsPromising( int grid[][], int row, int col, int num, Graphics canvas )
	{	
		int value = 0;
		if( (value = UsedInRow( grid, row, num )) != -1) 
			return (value | (row << 8));
		if( (value = UsedInCol( grid, col, num )) != -1)
			return (col | (value << 8)) ;
		if( (value = UsedInBox( grid, row-(row%3), col-(col%3), num )) != -1)
		{
			return value;
		}
		//(value | (row << 8))
		//(col | (value << 8))
		return -1;
	}
	
	/* Takes a partially filled-in grid and attempts to assign values to
	  all unassigned locations in such a way to meet the requirements
	  for Sudoku solution (non-duplication across rows, columns, and boxes) */
	boolean SolveSudoku(Graphics canvas)
	{	
		int result = FindUnassignedLocation(m_nBoard);
		if( result == -1 ) return true;
		int row = result >> 8;
		int col = result & 0xff;
		int returnVal = 0;
		int temp = 0;
		
		for( int num=1; num<=9; num++ )
		{	
			returnVal = IsPromising( m_nBoard, row, col, num, canvas );
			//no conflict found, so we can continue our search
			if( returnVal == -1 )
			{	
				m_nBoard[row][col] = num;
				paint(canvas);
				if( SolveSudoku( canvas ) )
				{
					return true;
				}
				m_nBoard[row][col] = 0;
			}
			//shows the user where the first conflict is found
			else
			{
				canvas.setColor(Color.BLUE);
				canvas.clearRect(BOARDLEFT + col * SQUAREWIDTH,     
						BOARDTOP + row * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
				canvas.drawString(String.valueOf(Character.forDigit(num, 10)), BOARDLEFT + col * SQUAREWIDTH + 18,
						BOARDTOP + row * SQUAREHEIGHT + 27);
				canvas.drawRect( BOARDLEFT + col * SQUAREWIDTH,     
						BOARDTOP + row * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
				canvas.setColor(Color.RED);
				canvas.drawRect( BOARDLEFT + (returnVal & 0xff) * SQUAREWIDTH,     
						BOARDTOP + (returnVal >> 8) * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
				if(IsPromising( m_nBoard, row, col, num+1, canvas ) == -1)
				{
					temp = m_nBoard[row][col];
					m_nBoard[row][col] = num + 1;
				}
				paint(canvas);
				m_nBoard[row][col] = temp;
			}
		}

		return false; // this triggers backtracking
	}
}
