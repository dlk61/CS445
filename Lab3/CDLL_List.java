import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_List( String fileName, String insertionMode ) throws Exception
	{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );	
			while ( infile.ready() )
			{	@SuppressWarnings("unchecked") 
				T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
				if ( insertionMode.equals("atFront") )
					insertAtFront( data ); 	
				else if ( insertionMode.equals( "atTail" ) )
					insertAtTail( data ); 
				else
					die( "FATAL ERROR: Unrecognized insertion mode <" + insertionMode + ">. Aborting program" );
			}
			infile.close();
	}	
	
	private void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}
		
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
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


	// TACK A NEW NODE ONTO THE FRONT OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data)
	{
		// call insert at TAIL
		// move head ptr to point at new tail node
		// now you have actualy achieved an insert at front
		// this approach suggested in lecture by our Fall 2019 student RUH24 (props!)
		insertAtTail(data);
		head = head.getPrev();
	}


	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		// BASE CASE WRITTEN FOR YOU
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
	
	// RETURN TRUE/FALSE THIS LIST CONTAINS A NODE WITH DATA EQUALS KEY
	public boolean contains( T key )
	{
		if ((search(key) == null))
		{
			return false;
		}
		return true;
	}

	// RETURN REF TO THE FIRST NODE (SEARCH CLOCKWISE FOLLOWING next) THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
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
	
} // END CDLL_LIST CLASS