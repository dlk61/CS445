import java.io.*;
import java.util.*;

public class Boggle
{	
    static String[][] board;
    static ArrayList<String> matches = new ArrayList<String>();
    static ArrayList<String> dictionary = new ArrayList<String>();
    static String line;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;

	public static void main( String args[] ) throws Exception
	{	
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while ((line = reader.readLine()) != null) {
            dictionary.add(line);
        }
        reader.close();
		Collections.sort(dictionary);
		
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); // FOR EACH [R][C] THE WORD STARTS EMPTY


		// print your sorted list of real dictionary words found in the grid - one word per line
		Collections.sort(matches);
		for (int i = 0; i < matches.size(); i++)
		{
			System.out.println(matches.get(i));
		}
		
		//endTime =  System.currentTimeMillis(); // for timing
		//System.out.println("GENERATION COMPLETED: runtime= " + (endTime - startTime)/MILLISEC_PER_SEC);
		
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{	
		word += board[r][c];
		String oldVal = board[r][c];
        
		// if the word above is a valid dictionary word then add it to your hits/matches data structure 
		int place = Collections.binarySearch(dictionary, word);

		if ((((place > 0) || (place == 0))) && (!matches.contains(word)) && (word.length() > 2))
		{
			matches.add(word);
		}
	
		else if (place < 0)
		{
			int lo = -(place +1);
			int ind = -(lo + 1);
			place = -(ind + 1);
			
			if (!dictionary.get(place).startsWith(word) )
			{
				return;
			}
		}
		
		
		// NORTH IS [r-1][c]
		if ( r-1 >= 0 && board[r-1][c] != null )   // THE r-1 WILL CHANGE FOR EVEY BLOCK BELOW
		{	
			board[r][c] = null;
			dfs(r-1, c, word);
			board[r][c] = oldVal;
		}
		
		// NE is [r-1][c+1]
		if ( (r-1 >= 0) && (c+1 >= 0 && c+1 < board.length) && (board[r-1][c+1] != null))
		{
			board[r][c] = null;
			dfs(r-1, c+1, word);
			board[r][c] = oldVal;
		}

		// E is [r][c+1]
		if ((c+1 >= 0 &&  c+1 < board.length) && board[r][c+1] != null)
		{
			board[r][c] = null;
			dfs(r, c+1, word);
			board[r][c] = oldVal;
		}
		
		// SE is [r+1][c+1]
		if ( (r+1 >=0 && r+1 < board.length) && (c+1 >=0 && c+1 < board.length) && (board[r+1][c+1] != null))
		{
			board[r][c] = null;
			dfs(r+1, c+1, word);
			board[r][c] = oldVal;
		}
		
		// S is [r+1][c]
		if ( (r+1 >=0 && r+1 < board.length) && (board[r+1][c] != null))
		{
			board[r][c] = null;
			dfs(r+1, c, word);
			board[r][c] = oldVal;
		}
		
		//SW is [r+1][c-1]
		if ( (r+1 >= 0 && r+1 < board.length) && (c-1 >=0) && (board[r+1][c-1] != null))
		{
			board[r][c] = null;
			dfs(r+1, c-1, word);
			board[r][c] = oldVal;
		}
		
		// W is [r][c-1]
		if ( (c-1 >= 0) && (board[r][c-1] != null))
		{
			board[r][c] = null;
			dfs(r, c-1, word);
			board[r][c] = oldVal;
		}
		
		// NW is [r-1][c-1]
		if ( (r-1 >= 0) && (c-1 >= 0) && (board[r-1][c-1] != null))
		{
			board[r][c] = null;
			dfs(r-1, c-1, word);
			board[r][c] = oldVal;
		}
		
	} // END DFS ----------------------------------------------------------------------------
    static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int size = infile.nextInt();
		String[][] board = new String[size][size]; 
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				board[i][k] = infile.next();
			}
		}
		infile.close();
		return board;
	} //END LOADBOARD 

} // END BOGGLE CLASS
