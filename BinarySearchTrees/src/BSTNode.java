

public class BSTNode
{
	BSTNode m_objLeftNode, m_objRightNode, m_objParentNode;
	int m_nKeyValue, subtreeSize = 1, rank = 0;
	BST bst = BinarySearchTrees.m_objBST;
	
	public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objParentNode = m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value to default of 0.
		m_nKeyValue = 0;
	}

	public BSTNode(int nKeyValue)
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		m_objLeftNode = m_objRightNode = null;
		
		// Set this node's key value
		m_nKeyValue = nKeyValue;
		
		m_objParentNode = bst.SearchParent(nKeyValue);
		
		bst.incrementSubTreeSize();
		
		rank = bst.getRank(this);
	}
	
	

	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		m_objLeftNode = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		m_objRightNode = objRightNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( m_objLeftNode );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( m_objRightNode );
	}
	
// Accessor method to set the parent node.
	public void SetParentNode( BSTNode objParentNode)
	{
		// Assign the parent node object reference.
		m_objParentNode = objParentNode;
	}
		
	// Accessor method to get the parent node object.
	public BSTNode GetParentNode()
	{
		 //Return the object.
		return( bst.SearchParent( m_nKeyValue ));
	}

	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		m_nKeyValue = nKeyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( m_nKeyValue );
	}

	//Accessor method to get the subtreesize of the tree rooted at node
	public int getSubTreeSize()
	{
		return subtreeSize; 
	}
	
	//Accessor method to set the size of a node's subtreeS
	public void SetSubTreeSize(int value)
	{
		subtreeSize = value;
	}
	
	public BSTNode getRootNode()
	{
		return bst.m_objRootNode;
	}
}
