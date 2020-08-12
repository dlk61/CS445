import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext( newNode );
			newNode.setPrev( newNode );
			head = newNode;
			return;
		}
		// NOT EMPTY. INSERT NEW NODE AFTER THE LAST/TAIL NODE
		head.getPrev().setNext(newNode);
		newNode.setPrev(head.getPrev());

		head.setPrev(newNode);
		newNode.setNext(head);
	}

	
	public int size()
	{	
		if (head == null)
		{
			return 0;
		}
		CDLL_Node cur = head;
		int size = 1;
		while (cur.getNext() != head)
		{
			cur = cur.getNext();
			size++;
		}
		return size;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{	
		CDLL_Node<T> cur = head;
		if (cur.getData().equals(key))
		{
			return cur;
		}
		while (cur.getNext() != head)
		{
			if (cur.getData().equals(key))
			{
				return cur;
			}
			cur = cur.getNext();
		}
		if (cur.getData().equals(key))
		{
			return cur;
		}
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		if (head == null)
		{
			return null;
		}
		String toString = "";
		
		CDLL_Node<T> cur = head;

		while (cur.getNext() != head)
		{
			toString += cur.getData() + "<=>";
			cur = cur.getNext();
		}
		toString += cur.getData();
		
		return toString;
		
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		deadNode.getNext().setPrev(deadNode.getPrev());
		deadNode.getPrev().setNext(deadNode.getNext());

	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() < 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		
		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();
			
			System.out.println( "stopping on " + deadNode + " to delete " + deadNode);
			
			removeNode(deadNode);
			// now you gotta worry about what if head was pointinng to the same node as DeadNode pointed to?
			// if it was them make head point to whicher node you are about to make curr point to
			if ((head == curr) && (skipCount > 0))
			{
				head = curr.getNext();
			}
			if ((head == curr) && (skipCount < 0))
			{
				head = curr.getPrev();
			}
			
			// adjust head to point to either deadNodes prev or deadNodes next ( hint are you CLOCKWISE or COUNTER )
			// you HAVE/MUST do this now cuase you are about to print the list. If you don't - list is FUBAR!!
			
			System.out.println("deleted. list now: " + toString());
			
			// if the list size has reached 1 break out of this loop YOU ARE DONE 
			if (size() == 1)
			{
				break;
			}	
			
			// othewise make curr point to same thing you just made head point to so you can resume the ritual
			String direction = "CLOCKWISE";
			curr = curr.getNext();
			if (skipCount < 0)
			{
				curr = curr.getPrev();
				direction = "COUNTER_CLOCKWISE";
			}
			
			// ** println("resuming at Mosse, skipping Mosse + 4 nodes CLOCKWISE after");
			System.out.println("resuming at " + curr + ", skipping " + curr + " + " + (Math.abs(skipCount) - 1) + " nodes " + direction + " after");
			
			// write loop that advance curr skipCount times (be sure of CLOCKWISE or COUNTER)
			if (skipCount > 0)
			{
				for (int i = 1; i <= skipCount; i++)
				{
					curr = curr.getNext();
				}
			}	
			if (skipCount < 0)
			{
				for (int i = 1; (i <= Math.abs(skipCount)); i++)
				{
					curr = curr.getPrev();
				}
			}
			
			// String junk = kbd.nextLine();  <= MIGHT FIND THis HELPFUL. FOR DEBUGGING. WAITS FOR YOU TO HIT RETUN KEY
			//Scanner kbd = new Scanner (System.in);
			//String junk = kbd.nextLine();
			
		}
		while (size() > 1 );

	}
	
} // END CDLL_LIST CLASS