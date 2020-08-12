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
	void enqueue( BSTNode<T>  node ) { queue.addLast( node ); }
	BSTNode<T>  dequeue() { return queue.removeFirst(); }
	// THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}

////////////////////////////////////////////////////////////////////////////////
class BSTreeP6<T>
{
	private BSTNode<T> root;
	private boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public BSTreeP6( String infileName ) throws Exception
	{
		root=null;
		Scanner infile = new Scanner( new File( infileName ) );
		while ( infile.hasNext() )
			add( (T) infile.next() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	public int size()
	{
		return countNodes(); 
	}

	// DUPES BOUNCE OFF & RETURN FALSE ELSE INCR COUNT & RETURN TRUE
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
    } // END addHelper


	// ITS A SEARCH - ONE OF FEW OPS YOU CAN DO ITERATIVELY
	public boolean contains( T key )
	{
		return contains( this.root, key  );
	}
	@SuppressWarnings("unchecked")
	private boolean contains( BSTNode<T> root, T key  )
	{
		if (root == null) return false;
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 ) return true;
		if (comp < 0) return contains(root.left, key );
		return contains(root.right, key );
	}

	public int countNodes()
	{
		return countNodes( root );
	}
	private int countNodes( BSTNode root)
	{
		if (root==null) return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
		
	public int countLevels()
	  {
		return countLevels( root ); 
	  }
	  private int countLevels( BSTNode root)
	  {
		if (root==null) return 0;
		return 1 + Math.max( countLevels(root.left), countLevels(root.right) );
	  }
	  
	  public int[] calcLevelCounts()
	  {
		int levelCounts[] = new int[countLevels()];
		calcLevelCounts( root, levelCounts, 0 );
		return levelCounts;
	  }
	  private void calcLevelCounts( BSTNode root, int levelCounts[], int level )
	  {
		if (root==null)return;
		++levelCounts[level];
		calcLevelCounts( root.left, levelCounts, level+1 );
		calcLevelCounts( root.right, levelCounts, level+1 );
	  }

	// INORDER TRAVERSAL REQUIRES RECURSION
	public void printInOrder()
	{
		printInOrder( this.root );
		System.out.println();
	}
	private void printInOrder( BSTNode<T> root )
	{
		if (root == null) return;
		printInOrder( root.left );
		System.out.print( root.key + " " );
		printInOrder( root.right );
	}

	public void printLevelOrder()
	{	if (this.root == null) return;
		Queue<T> q = new Queue<T>();
		q.enqueue( this.root ); // this. just for emphasis/clarity
		while ( !q.empty() )
		{	BSTNode<T> n = q.dequeue();
			System.out.print( n.key + " " );
			if ( n.left  != null ) q.enqueue( n.left );
			if ( n.right != null ) q.enqueue( n.right );
		}
	}

	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
	// DO NOT MODIFY ANYTHING ABOVE THIS LINE.  YOU FILL IN ALL THE CODE BELOW
	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

	// return true only if it finds/removes the node
	@SuppressWarnings("unchecked")
	public boolean remove( T key2remove )
	{
		BSTNode<T> parent = parentOf(key2remove);
		BSTNode<T> deadNode = null;

		Comparable cmpkey = (Comparable) key2remove;
		
		if (parent == null)
		{
			if (root != null)
			{
				if (cmpkey.compareTo(root.key) == 0)
				{
					if ((root.right == null) && (root.left == null))
					{
						root = null;
						return true;
					}
					else if ((root.right == null) && (root.left != null))
					{
						root = root.left;
						return true;
					}
					else if ((root.right != null) && (root.left == null))
					{
						root = root.right;
						return true;
					}
					else if ((root.right != null) && (root.left != null))
					{
						BSTNode<T> temp = root.left;
						while (temp.right != null)
						{
							temp = temp.right;
						}
						remove(temp.key);
						root.key = temp.key;

						return true;
					}
				}
			}
			return false;
			
		}
		Comparable cmpparent = (Comparable) parent.key;
		
		if (cmpkey.compareTo(cmpparent) < 0)
		{
			deadNode = parent.left;
		}
		else if (cmpkey.compareTo(cmpparent) > 0)
		{
			deadNode = parent.right;
		}
		else
		{
			return false;
		}

		if ((deadNode.right == null) && (deadNode.left == null))
		{
			if (deadNode == parent.left)
			{
				parent.left = null;
				
			}
			if (deadNode == parent.right)
			{
				parent.right = null;
				
			}
			return true;
		}

		else if (((deadNode.right == null) && (deadNode.left != null)) || ((deadNode.right != null) && (deadNode.left == null)))
		{
			if ((deadNode == parent.left) && (deadNode.left != null))
			{
				parent.left = deadNode.left;
			}
			else if ((deadNode == parent.left) && (deadNode.right != null))
			{
				parent.left = deadNode.right;
			}
			else if ((deadNode == parent.right) && (deadNode.left != null))
			{
				parent.right = deadNode.left;
			}
			else if ((deadNode == parent.right) && (deadNode.right != null))
			{
				parent.right = deadNode.right;
			}
			return true;
		}
		else if ((deadNode.left != null) && (deadNode.right != null))
		{
			BSTNode<T> temp = deadNode.left;
			while (temp.right != null)
			{
				temp = temp.right;
			}
			remove(temp.key);
			deadNode.key = temp.key;

			return true;
		}
		return false;
		
	}
	@SuppressWarnings("unchecked")
	BSTNode<T> parentOf(T key)
	{
		BSTNode<T> cur = root;
		BSTNode<T> parent = null;
		if (key == null)
		{
			parent = null;
		}
		if (key == cur)
		{
			parent = null;
		}

		Comparable cmpkey = (Comparable) key;

		while ((cur != null))
		{
			if (cmpkey.compareTo(cur.key) < 0)
			{
				parent = cur;
				if (cur.left != null)
				{
					cur = cur.left;
				}
				else
				{
					parent = null;
					break;
				}
				if ((cmpkey.compareTo(cur.key) != 0))
				{
					parent = null;
				}
			}
			else if (cmpkey.compareTo(cur.key) > 0)
			{
				parent = cur;
				if (cur.right != null)
				{
					cur = cur.right;
				}
				else
				{
					parent = null;
					break;
				}
				if (cmpkey.compareTo(cur.key) != 0)
				{
					parent = null;
				}
			}
			else 
			{
				break;
			}
		}
		return parent;
	}
 
}