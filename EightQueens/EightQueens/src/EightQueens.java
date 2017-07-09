/*Guillermo Alicea
 * COP 3503C - 0013
 * Recitation Assignment 7.5 - EightQueens
 * 03/14/16
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;

public class EightQueens extends Applet implements MouseListener, 
MouseMotionListener, Runnable, ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image m_imgQueen;
	MediaTracker tracker = new MediaTracker(this);
	static final int NUMROWS = 8; 
	static final int NUMCOLS = 8; 
	static final int SQUAREWIDTH = 50; 
	static final int SQUAREHEIGHT = 50; 
	static final int BOARDLEFT = 50; 
	static final int BOARDTOP = 50; 
	int m_nBoard[][] = new int[8][8]; 
	boolean m_bClash = false, check = true;
	
	public void init() 
	{
		setSize(1020,700);
		
		try 
		{ 	
			m_imgQueen =
					ImageIO.read(EightQueens.class.getResourceAsStream("queen.png")); tracker.addImage(m_imgQueen, 100);
					tracker.waitForAll();
		} 
		catch (Exception e1) { }
	}
	
	public void paint (Graphics canvas) 
	{  
		m_bClash = false;  
		DrawSquares( canvas );  
		canvas.setColor(Color.RED);  
		CheckColumns( canvas );
		CheckRows( canvas );  
		CheckDiagonal1( canvas );  
		CheckDiagonal2( canvas );  
		canvas.setColor(Color.BLUE);  
		canvas.drawString("EightQueens",
				BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
	} 
	
	void DrawSquares( Graphics canvas )
	{  
		canvas.setColor(Color.BLACK); 
		
		//delay
		try
		{
			Thread.sleep(100);
		}
		catch(Exception el){}
		
		canvas.clearRect(0, 0, 1020, 700);
		
		for( int nRow=0; nRow<NUMROWS; nRow++ )  
		{   
			for( int nCol=0; nCol<NUMCOLS; nCol++ )   
			{    
				canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,     
						BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );         
				if( m_nBoard[nRow][nCol] != 0 )    
				{     
					canvas.drawImage( m_imgQueen,      
							BOARDLEFT + nCol * SQUAREWIDTH + 8, 
							BOARDTOP + nRow * SQUAREHEIGHT + 6, null );    
					   
				}  
			} 
		}
		
		//Solves the puzzle once and only once
		if(check)
		{
			check = false;
			SolveQueen(0, canvas);
			paint(canvas);
		}
	}
	
	void CheckColumns( Graphics canvas ) 
	{  
		// Check all columns  
		for(  int nCol=0; nCol<NUMCOLS; nCol++ )  
		{   
			int nColCount = 0;   
			for( int nRow=0; nRow<NUMROWS; nRow++ )   
			{    
				if( m_nBoard[nRow][nCol] != 0 )    
				{     
					nColCount++;    
				}   
			}   
			if( nColCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),     
						BOARDTOP + ( SQUAREHEIGHT / 2 ),      
						BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),     
						BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) ); 
			     m_bClash = true;   
			} 
		}
	}
	
	void CheckRows( Graphics canvas ) 
	{  
		for(  int nRow=0; nRow<NUMROWS; nRow++ )  
		{   
			int nRowCount = 0;   
			for( int nCol=0; nCol<NUMCOLS; nCol++ )   
			{    
				if( m_nBoard[nRow][nCol] != 0 )    
				{     
					nRowCount++;    
				}   
			}   
			if( nRowCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),     
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),      
						BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),     
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );        
				m_bClash = true;   
			}  
		} 
	} 
	
	void CheckDiagonal1( Graphics canvas ) 
	{  
		// Check diagonal 1    
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )  
		{   
			int nCol = 0; 
			int nThisRow = nRow;   
			int nThisCol = nCol;   
			int nColCount = 0;       
			
			while( nThisCol < NUMCOLS &&    nThisRow < NUMROWS )   
			{    
				if( m_nBoard[nThisRow][nThisCol] != 0 )    
				{     
					nColCount++;    
				}    
				nThisCol++;    
				nThisRow++;   
			}       
			if( nColCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),       
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );         
				m_bClash = true;   
			}  
		}
		
		for( int nCol=1; nCol<NUMCOLS; nCol++)  
		{   
			int nRow = 0;      
			int nThisRow = nRow;   
			int nThisCol = nCol;   
			int nColCount = 0; 
			
			while( nThisCol < NUMCOLS &&    nThisRow < NUMROWS )   
			{    
				if( m_nBoard[nThisRow][nThisCol] != 0 )    
				{     
					nColCount++;    
				}    
				nThisCol++;    
				nThisRow++;   
			}       
			if( nColCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),       
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );         
				m_bClash = true;   
			}  
		} 
	} 
	
	void CheckDiagonal2( Graphics canvas ) 
	{  
		// Check diagonal 2     
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )  
		{   
			int nCol = NUMCOLS - 1;       
			int nThisRow = nRow;   
			int nThisCol = nCol;   
			int nColCount = 0; 
			
			while( nThisCol >= 0 &&    nThisRow < NUMROWS )   
			{    
				if( m_nBoard[nThisRow][nThisCol] != 0 )    
				{     
					nColCount++;    
				}    
				nThisCol--;    
				nThisRow++;   
			}       
			if( nColCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),       
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );         
				m_bClash = true;   
			}  
		}  
		for( int nCol=NUMCOLS-1; nCol>=0; nCol--)  
		{   
			int nRow = 0;      
			int nThisRow = nRow;   
			int nThisCol = nCol;   
			int nColCount = 0;       
			
			while( nThisCol >= 0 &&    nThisRow < NUMROWS )   
			{    
				if( m_nBoard[nThisRow][nThisCol] != 0 )    
				{ 
					nColCount++;    
				}    
				nThisCol--;    
				nThisRow++;   
			}       
			if( nColCount > 1 )   
			{    
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),       
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),      
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );         
				m_bClash = true;   
			}      
		} 
	} 
	//Recursively solves the EightQueens problem
	boolean SolveQueen(int col, Graphics canvas)
	{	
		if( col >= NUMCOLS ) return true;

		for( int row=0; row < NUMROWS; row++ )
		{	
			m_nBoard[row][col] = 1;
			paint(canvas);
			if(m_bClash == false)
			{	
				if( SolveQueen(col+1, canvas) )
					return true;
				else
					m_nBoard[row][col] = 0;
			}
			else
			{
				m_nBoard[row][col] = 0;
				m_bClash = false;
			}
		}

		return false; // this triggers backtracking
	}
				

	/*public static void main(String[] args) 
	{
		

	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
