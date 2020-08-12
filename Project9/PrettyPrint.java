import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
class BSTNode<T>
{	T key;
	BSTNode<T> left,right;
	BSTNode( T key, BSTNode<T> left, BSTNode<T> right )
	{	this.key = key;
		this.left = left;
		this.right = right;
	}
}

///////////////////////////////////////////////////////////////////////////////////////
class Queue<T>
{	LinkedList<BSTNode<T>> queue;
	Queue() { queue =  new LinkedList<BSTNode<T>>(); }
	boolean empty() { return queue.size() == 0; }
	int size(){ return queue.size();}
	void enqueue( BSTNode<T>  node ) { queue.addLast( node ); }
	BSTNode<T>  dequeue() { return queue.removeFirst(); }
	// THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}
////////////////////////////////////////////////////////////////////////////////
@SuppressWarnings("unchecked")
public class PrettyPrint<T>
{
	BSTNode<T> root;
	boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public PrettyPrint()
	{
		root=null;
	}

	public PrettyPrint( String filename) throws Exception
	{
		root=null;
		BufferedReader infile = new BufferedReader( new FileReader( filename ) );
		while ( infile.ready() )
			add( (T) infile.readLine());
		infile.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean add( T key )
	{	addAttemptWasDupe=false;
		root = addHelper( this.root, key );
		return !addAttemptWasDupe;
	}

	@SuppressWarnings("unchecked")
	private BSTNode<T> addHelper( BSTNode<T> root, T key )
	{
		if (root == null) return new BSTNode<T>(key,null,null);
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 )
			{ addAttemptWasDupe=true; return root; }
		else if (comp < 0)
			root.left = addHelper( root.left, key );
		else
			root.right = addHelper( root.right, key );

		return root;
    } 


	void prettyPrint()
	{
		if (root == null)
			return;
		Queue<T> queue = new Queue<T>();
		queue.enqueue(this.root);
		int level = 0;
		while (true)
		{
			int nodeCount = queue.size();
			if(nodeCount == 0)
				break;
			for (int i=0;i<level;i++)
				System.out.print(" ");
			level++;
			while(nodeCount>0)
			{
				BSTNode<T> curr = queue.dequeue();
				System.out.print(curr.key + " ");
				if(curr.left != null)
					queue.enqueue(curr.left);
				if(curr.right != null)
					queue.enqueue(curr.right);
				nodeCount--;
			}
			System.out.println();
		}
	}

	public PrettyPrint<T> makeBalancedCopyOf()
	{
		ArrayList<T> keys = new ArrayList<T>();
		arrayInOrderHelper(root,keys);
		PrettyPrint<T> balancedBST = new PrettyPrint<T>();
		addKeysInBalancedOrder(keys,0,countNodes(this.root)-1,balancedBST);
		return balancedBST;

	}

	private int countNodes( BSTNode<T> root )
	{
		if (root==null) return 0;
		return 1 + countNodes( root.left ) + countNodes( root.right );
	}

	void arrayInOrderHelper(BSTNode<T> root, ArrayList<T> keys)
	{
		if(root==null)
			return;
		arrayInOrderHelper(root.left, keys);
		keys.add(root.key);
		arrayInOrderHelper(root.right, keys);
	}
	void addKeysInBalancedOrder(ArrayList<T> keys, int low, int hi, PrettyPrint<T> balancedBST)
	{
		int mid = (hi + low)/2;
		balancedBST.add(keys.get(mid));
		if (low > hi)
			return;
		addKeysInBalancedOrder(keys,low,mid-1,balancedBST);
		addKeysInBalancedOrder(keys,mid+1,hi,balancedBST);
	}


}