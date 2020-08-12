import java.io.*;
import java.util.*;

public class ArrBagTester
{
	public static void main(String args[]) throws Exception
	{
		if ( args.length < 2 ) die( "usage: java ArrBagTester <filename> <filename>\n" );
		// INSTANCE AN OBECT OF OUR GENERIC CLASS AND SPECIFY IT AS A CONTAINER OF TYPE ARRAY OF STRING
		
		ArrBag<String> bagStr1 = new ArrBag<String>( args[0] ); 	 
		System.out.println( "bagStr1 contains " + bagStr1.size() + " elements loaded from " + args[0] + ": "  + bagStr1 );
		
		ArrBag<String> bagStr2 = new ArrBag<String>( args[1]); 	
		System.out.println( "bagStr2 contains " + bagStr2.size() + " elements loaded from " + args[1] + ": "  + bagStr2 );
		
		bagStr1.clear(); bagStr2.clear();
		
		System.out.println( "bagStr1 contains " + bagStr1.size() + " elements after clear: "  + bagStr1 );
		System.out.println( "bagStr2 contains " + bagStr2.size() + " elements after clear: "  + bagStr2 );
	}
	
	static void die( String errMsg )
	{
		System.out.println( "\nFATAL ERROR: " + errMsg );
		System.exit(0);
	}
}
