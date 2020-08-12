//  STARTER FILE FOR ARRBAG

import java.io.*;
import java.util.*;

public class ArrBag<T>
{	
	final int NOT_FOUND = -1;
	final int INITIAL_CAPACITY = 1;
	private int count; // LOGICAL SIZE
	
	// DEFINE & INITIALIZE REF TO OUR ARRAY OF T OBJECTS
	@SuppressWarnings("unchecked") // SUPRESSION APPLIES TO THE NEXT LINE OF CODE
	T[] theArray = (T[]) new Object[INITIAL_CAPACITY]; // CASTING TO T[] (creates warning we have to suppress)
 
	@SuppressWarnings("unchecked")
	public ArrBag( String infileName ) throws Exception
	{
		count = 0; // i.e. logical size, actual number of elems in the array
		BufferedReader infile = new BufferedReader( new FileReader(  infileName ) );
		while ( infile.ready() )
			this.add( (T) infile.readLine() );
		infile.close();
	}
  
	// APPENDS NEW ELEM AT END OF LOGICAL ARRAY. UPSIZES FIRST IF NEEDED
	public boolean add( T element ) // THIS IS AN APPEND TO THE LOGICAL END OF THE ARRAY AT INDEX count
	{	if (element == null ) return false;
		if (size() == theArray.length) upSize(); // DOUBLES PHYSICAL CAPACITY
		theArray[ count++] = element; // ADD IS THE "setter" 
		return true; // success. it was added
	}
	
	// INCR EVERY SUCESSFULL ADD, DECR EVERY SUCESSFUL REMOVE
	public int size()
	{
		return count; 
	}

	// RETURNS TRUE IF THERE ARE NO ELEMENTS IN THE ARRAY, OTHERWISE FALSE
	public boolean isEmpty()
	{
		return size()==0;	 
	}

	public T get( int index ) throws Exception
	{
		if ( index < 0 || index >=size() ) 
			throw new Exception("attempt to get elem at non-existent index (" + index + ")\n" );
		return theArray[index];
	}
	
	// SEARCHES FOR THE KEY. TRUE IF FOUND, OTHERWISE FALSE
	public boolean contains( T key ) throws Exception
	{	if (key == null) return false;
		for ( int i=0 ; i < size() ; ++i )
			if ( get(i).equals( key ) ) // WE ARE MAKING AN ASSUMPTION ABOUT TYPE T... WHAT IS IT?
				return true;
		return false;
	}

	// --------------------------------------------------------------------------------------------
	// # # # # # # # # # # #   Y O U   W R I T E   T H E S E   M E T H O D S  # # # # # # # # # # #
	// --------------------------------------------------------------------------------------------

	// PERFORMS LOGICAL (SHALLOW) REMOVE OF ALL THE ELEMENTS IN THE ARRAY (SIMPLE 1 LINER!)
	public void clear()
	{
		count = 0;
	}
	
	// DOUBLE THE SIZE OF OUR ARRAY AND COPY ALL THE ELEMS INTO THE NEW ARRAY
	
	@SuppressWarnings("unchecked")
	private void upSize()
	{ 
		
		T[] biggerArray = (T[]) new Object[2 * theArray.length];
		for (int i = 0; (i < theArray.length); i++)
		{
			biggerArray[i] = theArray[i];
		}
		theArray = biggerArray;

	}
	
	
	// STUDY THE OUTPUT CAREFULY TO SEE EXACTLY WHAT GETS RETURNED
	public String toString() 
	{
		String toString = "";
		
		for (int i = 0; (i < count); i++)
		{
			toString += theArray[i];
			toString += " ";
		}
		return toString;
		
	}	
	
} // END ARRBAG CLASS