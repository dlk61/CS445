/*
	BinaryPrint.java
	uses ">>" and % operators to print a decimal value in binary
*/
import java.io.*;

public class Knapsack
{
	public static void main( String[] args ) throws Exception
	{
		int number; // java's char type is just a 16 bit number
		String filename = args[0];
		int val;
		int sum;
		int j;
		String StringOfSet;

		BufferedReader infile = new BufferedReader( new FileReader(filename) );	
		
		String line = infile.readLine();
        String[] arr = line.split(" ");
		for (int index = 0; (index < arr.length); index++)
		{
            System.out.print(arr[index] + " ");
		}
        
		String nextLine = infile.readLine();
        int target = Integer.parseInt(nextLine);
		System.out.println("\n" + target);
		infile.close();
        

		for (number=0 ; (number < (1<<arr.length)); ++ number)
		{
			sum = 0;
			j = 0;
			StringOfSet = "";
			
			for (int i = (arr.length-1) ; i >=0  ; --i )
			{
				/* shift i'th bit to the end position */
				if ( ((number >> i) % 2) == 1 ) /* true iff i'th bit is a 1 */
				{
					val = Integer.parseInt(arr[j]);
					sum += val;
					StringOfSet = StringOfSet + arr[j] + " ";
				}	
		
				j++;	
			}
			if (sum == target)
			{
				System.out.println(StringOfSet);
			}		
		}
	} // END MAIN
} // END CLASS