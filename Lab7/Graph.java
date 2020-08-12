/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private Edge G[];              

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   
		G = new Edge[dimension]; 		
		numEdges=0;

		while ( graphFile.hasNextInt() )
		{
			addEdge(graphFile.nextInt(), graphFile.nextInt(), graphFile.nextInt());
			
		}

	} // END readGraphFile

	private void addEdge( int src, int dst, int weight )
	{
		insertAtFront(src, new Edge(dst, weight));
		++numEdges;
	}
	private void insertAtFront(int src, Edge edge)
	{
		edge.setNext(G[src]);
		G[src] = edge;
	}

	
   private boolean hasEdge(int fromNode, int toNode)
   {
	int x1 = 0;
	int y1 = 0;
	int x2 = G.length;
	int y2 = G.length;

	if ((fromNode >= y1 && fromNode <= y2) && (toNode >= x1 && toNode <= y2))
	{
		return true;
	}
	return false;
   }

	private int inDegree(int node)
	{
		numEdges = 0;
		
		for (int i = 0; i < G.length; i++)
		{
			Edge cur = G[i];

			while (cur != null) {
				if (cur.dst == node)
				{
					numEdges++;
				}
				cur = cur.next;
			}
		}
		return numEdges;
	}

	private int outDegree(int node)
	{
		numEdges = 0;
		Edge cur = G[node];
		while (cur != null) {
			numEdges++;
			cur = cur.next;
		}
		return numEdges;
	}

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

		for (int row = 1; row < G.length; row++)
		{
			if (inDegree(row) > winner)
			{
				winner = inDegree(row);
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

		for (int row = 1; row < G.length; row++)
		{
			if (inDegree(row) < winner)
			{
				winner = inDegree(row);
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
		try
		{
			if (hasEdge(fromNode, toNode) == true)
			{

				if (G[fromNode].dst == toNode)
				{
					G[fromNode]= G[fromNode].next;
					return;
				}

				Edge cur = G[fromNode];
			
				while (cur.next != null)
				{
					if (cur.next.dst == toNode)
					{
						cur.setNext(cur.next.next);
					}
					cur = cur.next;
					return;
				}
				
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
		for (int r=0 ; r < G.length ; r++ )
		{
			Edge cur = G[r];
			the2String += String.format(r + ":");
			while (cur != null) {
				the2String += String.format(" -> [" + cur.dst + "," + cur.weight + "]");
				cur = cur.next;
			}
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING
	

} //EOF

class Edge{
	int dst;
	int weight;
	Edge next;
	public Edge(int dst, int weight) {
		this.dst = dst;
		this.weight = weight;
	}
	public void setNext(Edge next) {
		this.next = next;
	}
}
