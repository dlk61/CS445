
/******************************************************************************

                           Online Java Compiler.
               Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main2
{
	public static void main(String[] args) {
		int number; // java's char type is just a 16 bit number
		int i;
		int val;
		int sum = 0;
		int target = 30;
		String[] r = {"10","15","20","30"};

		System.out.println("       B I T   P O S I T I O N");
		System.out.println("       1111100000000000");
		System.out.println("       5432109876543210");
		System.out.println("       ----------------");
		

		for (number=0 ; number  < (1<<4) ; ++ number)
		{
	     	sum = 0;
		    for (i = 15 ; i >=0  ; --i )                   
		    {
				/* shift i'th bit to the end position then AND it with 1 */
				if ( (number >> i) % 2 == 1 ) /* true iff i'th bit is a 1 */
				{
				  val = Integer.parseInt(r[i]);
				  sum += val;
				}
					
			}
		
			if (sum == target)
			{
			    System.out.println(" ");
   			for (i = 15 ; i >=0  ; --i )
		    {
				/* shift i'th bit to the end position then AND it with 1 */
				if ( (number >> i) % 2 == 1 ) /* true iff i'th bit is a 1 */
				{
  		    
				 val = Integer.parseInt(r[i]);
				 System.out.printf(val + " ");
					 

				}
					
			}
			
			}
			
		}
	}
}