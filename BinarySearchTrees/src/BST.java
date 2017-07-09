
public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	public BSTNode m_objRootNode;
	
	// Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
    	int k = 3;
 
    	if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        }
        
    	if(Math.abs(objNode.GetKeyValue() - nKeyValue) < k)
    		return objNode;
    	
        // Here we need to walk left.
        else if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) );
        }
        
        // Here we need to talk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) );
        }
    
        return( objNode );
    }
    
    public BSTNode Delete(int nValue)
    {	
    	BSTNode returnNode = Search(nValue);
    	//only decrement/delete if the value is in the tree 
    	//otherwise, no need to delete/decrement
    	if(Search(nValue) != null)
    	{	
    		returnNode = Delete(Search(nValue));
    		decrementSubTreeSize(m_objRootNode);
    	}
    	
    	return returnNode;
    }
    
    private BSTNode Delete(BSTNode ds)
    {	
    	if(ds == null) return null;
    	
    	//case of no children
    	if(ds.GetLeftNode() == null && ds.GetRightNode() == null)
    	{	
    		if(ds.GetParentNode() == null) return null;
    		if(ds.GetParentNode().GetLeftNode() != null && ds.GetParentNode().GetLeftNode().GetKeyValue() != ds.GetKeyValue())
    		{	ds.GetParentNode().SetLeftNode(null); return null;}
    		else
    		{	ds.GetParentNode().SetRightNode(null); return null;}
    	}
    	//case of one child (right)
    	else if(ds.GetLeftNode() == null && ds.GetRightNode() != null)
    	{
    		ds.SetKeyValue(ds.GetRightNode().GetKeyValue());
    		ds.SetRightNode(ds.GetRightNode().GetRightNode());
    		ds.SetLeftNode(ds.GetRightNode().GetLeftNode());
    		return ds;
    	}
    	//case of one child (left)
    	else if(ds.GetRightNode() == null)
    	{
    		ds.SetKeyValue(ds.GetLeftNode().GetKeyValue());
    		ds.SetLeftNode(ds.GetLeftNode().GetRightNode());
    		ds.SetLeftNode(ds.GetLeftNode().GetLeftNode());
    		return ds;
    	}
    	//case of two children
    	else
    	{	
    		int tempKey = getMin(ds.GetRightNode());
    		Delete(Search(tempKey));
    		ds.SetKeyValue(tempKey);
    	}	
    	
    	return ds;
    }
    
    public int getMin(BSTNode ds)
    {
    	if(ds.GetLeftNode() == null) return ds.GetKeyValue();
    	return getMin(ds.GetLeftNode());
    }
    
    //could be used in our delete function in place of getMin
    //(however we would have the go the opposite path)
    public int getMax(BSTNode ds)
    {
    	if(ds.GetRightNode() == null) return ds.GetKeyValue();
    	return getMin(ds.GetRightNode());
    }
    
    public BSTNode SearchParent( int nKeyValue )
    {
        return( SearchParent( m_objRootNode, nKeyValue ) );
    }
    
    //Search for parent of objNode
    public BSTNode SearchParent( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null)
    	{
    		return( null );
    	}
    	
    	int nLeftValue = 0, nRightValue = 0;
    	
    	if(objNode.GetLeftNode() != null)
    		nLeftValue = objNode.GetLeftNode().GetKeyValue();
    	if(objNode.GetRightNode() != null)
    		nRightValue = objNode.GetRightNode().GetKeyValue();
    	
    	if(nLeftValue != 0)
    		if(nLeftValue == nKeyValue) return objNode;
    		else if( nLeftValue > nKeyValue )
    	{
    		objNode = objNode.GetLeftNode();
    		return SearchParent(objNode, nKeyValue);
    	}
            
    	if(nRightValue != 0)
    		if(nRightValue == nKeyValue) return objNode;
    		else if( nRightValue < nKeyValue )
    	{
    		objNode = objNode.GetRightNode();
    		return SearchParent(objNode, nKeyValue);
    	}
    	
    	return( null );
    	
	}
    
    //Recursively compute the subTreeSize of the subtree rooted at
    //BSTNode ds
    public int size(BSTNode ds, boolean check)
    {
    	if(ds == null) return 0;
    	
    	if(check) return size(ds.GetLeftNode(), false)
    			+ size(ds.GetRightNode(), false);
    	return 1 + size(ds.GetLeftNode(), false)
		+ size(ds.GetRightNode(), false);
    }
    
    public int getRank(BSTNode ds)
    {
    	if(ds == null) return 0;
    	return rankFinder(ds.GetKeyValue(), m_objRootNode);
    }
    
    public int getRank(int nValue)
    {
    	return rankFinder(nValue, m_objRootNode);
    }
    
  //compute the rank of a node recursively
    private static int rankFinder(int nValue, BSTNode ds)
    {
    	if(ds == null) return 0;
    	if(ds.GetKeyValue() == nValue) return 1;
    	return 1 + rankFinder(nValue, ds.GetLeftNode())
    			 + rankFinder(nValue, ds.GetRightNode());
    }
    //Increment subtreeSize for each node when we insert,
    //decrement when we deleteSSS
    public void incrementSubTreeSize()
    {
    	incrementSubTreeSize(m_objRootNode);
    }
    
    public static void incrementSubTreeSize(BSTNode root)
    {
    	if(root == null) return;
    	
    	root.SetSubTreeSize(root.getSubTreeSize() + 1);
    	incrementSubTreeSize(root.GetLeftNode());
    	incrementSubTreeSize(root.GetRightNode());
    }
    
    public static void decrementSubTreeSize(BSTNode root)
    {
    	if(root == null) return;
    	
    	root.SetSubTreeSize(root.getSubTreeSize() - 1);
    	decrementSubTreeSize(root.GetLeftNode());
    	decrementSubTreeSize(root.GetRightNode());
    }
}
