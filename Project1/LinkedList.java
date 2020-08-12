import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	@SuppressWarnings("unchecked")	
	public LinkedList( String fileName, boolean orderedFlag )
	{
		head=null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print


	public String toString()
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	
	public int size() // OF COURSE MORE EFFICIENT TO MAINTAIN COUNTER. BUT YOU WRITE LOOP!
	{
		int count = 0;
		Node cur = head;
		while (cur != null)
		{
			++count;
			cur = cur.getNext();
		}
		return count;
	}

	public boolean empty()
	{
		return (this.size() == 0) ? true: false;
	}

	
	public boolean contains( T key )
	{
		return (search(key) == null) ? false: true;
	}

	
	public Node<T> search( T key )
	{
		if (head == null)
		{
			return null;
		}
		Node<T> curr = head;
		while (curr != null)
		{
			if (curr.getData().equals(key))
			{
				return curr;
			}
			curr = curr.getNext();
		}
		return curr;
	}

	
	public void insertAtTail(T data) // TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	{
		if (head == null){
			this.head = new Node<T>(data);
		}
		else {

			Node<T> cur = this.head;
			while(cur.getNext() != null)
			{
				cur = cur.getNext();
			}
			cur.setNext(new Node<T>(data));
		}	
	}

	@SuppressWarnings("unchecked")
	public void insertInOrder(T data) // PLACE NODE IN LIST AT ITS SORTED ORDER POSTIOPN
	{
		Comparable cmpData = (Comparable) data;

		if ((head == null) || (cmpData.compareTo(head.getData()) < 0))
		{
			// insert at front
			insertAtFront(data);
			return;
		}
		Node<T> curr = head; // WE KNOW HEAD !NULL
		// cdata > curr.next.data
		while (curr.getNext()!=null && cmpData.compareTo( curr.getNext().getData() ) > 0 )
		{
			curr = curr.getNext();
		}	

		curr.setNext( new Node(data, curr.getNext()) );
	}
	
	
	public boolean remove(T key) // FIND/REMOVE 1st OCCURANCE OF A NODE CONTAINING KEY
	{
		if (head == null)
		{
			return false;
		}
		if (head.getData().equals(key))
		{
			head = head.getNext();
			return true;
		}
		Node<T> cur = head;
		while (cur.getNext() != null)
		{
			if (cur.getNext().getData().equals(key))
			{
				cur.setNext(cur.getNext().getNext());
				return true;
			}
			cur = cur.getNext();
		}
		return false;
	}

	
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> cur = head;
		if (cur == null)
		{
			return false;
		}
		if (size() == 1)
		{
			return removeAtFront();
		}
		while (cur.getNext().getNext() != null)
		{
			cur = cur.getNext();
		}

		cur.setNext(null);
		return true;
		
    }

	
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> cur = head;
		if (cur != null)
		{
			remove(cur.getData());
			return true; 
		}
		return false;
		
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> unionList = new LinkedList<T>();

		Node<T> cur = this.head;
		
		if (cur != null)
		{
			if (!unionList.contains(cur.getData()))
			{
				unionList.insertInOrder(cur.getData());

				while (cur.getNext() != null)
				{
					if (!unionList.contains(cur.getNext().getData()))
					{
						unionList.insertInOrder(cur.getNext().getData());
					}	
					cur = cur.getNext();
				}
			}
		}
		
		cur = other.head;
		if (cur != null)
		{
			if (!unionList.contains(cur.getData()))
			{
				unionList.insertInOrder(cur.getData());

				while (cur.getNext() != null)
				{
					if (!unionList.contains(cur.getNext().getData()))
					{
						unionList.insertInOrder(cur.getNext().getData());
					}	
					cur = cur.getNext();
				}
			}
		}
		
		return unionList; // CHANGE TO YOUR CODE
	}
	
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> interList = new LinkedList<T>();

		if ((this.head != null) && (other.head != null))
		{
			Node<T> cur = this.head;

			if (other.contains(cur.getData()))
			{
				interList.insertInOrder(cur.getData());
			} 
			while (cur.getNext() != null)
			{
				if ((other.contains(cur.getNext().getData()) && (!interList.contains(cur.getNext().getData()))))
				{
					interList.insertInOrder(cur.getNext().getData());
				}
				cur = cur.getNext();
			}
		}
		return interList;
	}


	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diffList = new LinkedList<T>();

		if ((this.head != null) && (other.head != null))
		{
			Node<T> cur = this.head;
			
			if (!other.contains(cur.getData()))
			{
				diffList.insertInOrder(cur.getData());
			} 
			while (cur.getNext() != null)
			{
				if (!other.contains(cur.getNext().getData()))
				{
					diffList.insertInOrder(cur.getNext().getData());
				}
				cur = cur.getNext();
			}
		}
		return diffList;
	}
	
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return (this.union(other).diff(this.inter(other)));
		
	}

} //END LINKEDLIST CLASS

