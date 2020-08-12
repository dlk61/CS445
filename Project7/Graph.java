/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
		
//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E 
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R 
//		      ........R E M O V E   A F T E R   T E S T I N G .......
/*
		for (int node = 0 ; node < G.length ; ++node )
		{
			System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
			System.out.format( "out(%d)=%d  ",node,outDegree(node) );
			System.out.format( "deg(%d)=%d\n",node,degree(node) );
		}
*/
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;

		// WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
		for (int row = 0; row < dimension; row++)
			for (int col = 0; col < dimension; col++)
		{
			if (row == col)
			{
				G[row][col] = 0;
			}
			else
			{
				G[row][col] = -1;
			}
		}
		

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			addEdge(graphFile.nextInt(), graphFile.nextInt(), graphFile.nextInt());
			// read in the row,col,weight // that eat us this line
			// call add edge
		}

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
  private boolean hasEdge(int fromNode, int toNode)
  {
	int x1 = 0;
	int y1 = 0;
	int x2 = 7;
	int y2 = 7;

	if ((fromNode >= y1 && fromNode <= y2) && (toNode >= x1 && toNode <= y2))
	{
		return true;
	}
	return false;
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		numEdges = 0;
		
		for (int row = 0; row < G.length; row++)
		{
			int value = G[row][node];
			if (value > 0 )
			{
				numEdges++;
			}
		}
		return numEdges;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		numEdges = 0;

		for (int col = 0; col < G.length; col++)
		{
			int value = G[node][col];
			if (value > 0 )
			{
				numEdges++;
			}
		}
		return numEdges;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int winner = outDegree(0);

		for (int row = 1; row < G.length; row++)
		{
			if (outDegree(row) > winner)
			{
				winner = outDegree(row);
			}
		}
		return winner;
	}

	public int maxInDegree()
	{
		int winner = inDegree(0);

		for (int col = 1; col < G.length; col++)
		{
			if (inDegree(col) > winner)
			{
				winner = inDegree(col);
			}
		}
		return winner;
	}

	public int minOutDegree()
	{
		int winner = outDegree(0);

		for (int row = 1; row < G.length; row++)
		{
			if (outDegree(row) < winner)
			{
				winner = outDegree(row);
			}
		}
		return winner;
	}
	public int minInDegree()
	{
		int winner = inDegree(0);

		for (int col = 1; col < G.length; col++)
		{
			if (inDegree(col) < winner)
			{
				winner = inDegree(col);
			}
		}
		return winner;
	}	
	
	public int maxDegree()
	{
		int winner = degree(0);
		for (int row = 1; row < G.length; row++)
		{
			for (int col = 1; col < G.length; col++)
			{
				if (row == col)
				{
					degree(row);
				}
				if (degree(row) > winner)
				{
					winner = degree(row);
				}
			}
		}
		return winner;

	}

	public int minDegree()
	{
		int winner = degree(0);
		for (int row = 1; row < G.length; row++)
		{
			for (int col = 1; col < G.length; col++)
			{
				if (row == col)
				{
					degree(row);
				}
				if (degree(row) < winner)
				{
					winner = degree(row);
				}
			}
		}
		return winner;
	}
	
	public void removeEdge(int fromNode, int toNode)
	{
		/* if caller passes in a row col pair that 
		   out of bound or has no edge there, you must 
		   throw and catch an exception. See my output.
		
		   if the edge is there then remove it by writing 
		   in a NO_EDGE value at that coordinate in the grid	
		*/
		try
		{
			if (hasEdge(fromNode, toNode) == true)
			{
				G[fromNode][toNode] = NO_EDGE;
			}
			else
			{
				throw new Exception("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + fromNode + "," + toNode + ")");
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF

