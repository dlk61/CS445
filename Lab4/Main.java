import java.io.*;
import java.util.*;

public class Main
{
	public static void main( String[] args ) throws Exception
	{
		String filename = args[0];
		int size = 3;
		int count = 0;
		int sum = 0;
		String byte_str = "";
		long byte_int = 0L;
        int target = 0;
        int val = 0;
		BufferedReader infile = new BufferedReader( new FileReader(filename) );	
	
		@SuppressWarnings("unchecked")
		String line = infile.readLine();
        String[] r = line.split(" ");
		for (int i = 0; (i < size); i++)
		{
            System.out.print(r[i] + " hi");
		}
        
		String nextLine = infile.readLine();
        target = Integer.parseInt(nextLine);
		System.out.println("\n" + target);
		
		infile.close();
        

        for (int i = 0; (i < (1<<size)); i++)
        {
            count += 1;
            sum = 0;
            val = 0;
		    byte_str = Integer.toBinaryString(count);
            System.out.println(byte_str);
            byte_int = Long.parseLong(byte_str);
         
            int y = 0;
            for (int j = (size-1); (j>=0); --j)
		    {
			    if (((byte_int >> j) % 2) == 1)
			    {
                    val = Integer.parseInt(r[y]);
                    sum += val;
                    System.out.println("val is " + val);
                    System.out.println("sum is " + sum);
                }
                y += 1;

            }    
            if (sum == target)
		    {
                System.out.println("byte str is " + byte_str);
                
			    for (int x = 0; (x < size); x++)
			    {
				    if (byte_str.charAt(x) == '1')
				    {
                        //System.out.println("hello");
					        
				    }
                }
                
            }	
            
        }
        
        
	}
}