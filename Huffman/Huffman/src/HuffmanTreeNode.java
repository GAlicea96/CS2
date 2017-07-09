
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
	private int freq = 0;
	private byte value = 0;
	private HuffmanTreeNode parent = null;
	private HuffmanTreeNode left = null, right = null;
	
	//Node Constructor for an actual byte vale
	public HuffmanTreeNode(int freq, byte value)
	{	
		this.freq = freq;
		this.value = value;
	}
	
	//Node Constructor for a "combined" node 
	public HuffmanTreeNode(int freq1, int freq2)
	{
		this.freq = freq1 + freq2;
		this.value = -1;
	}
	
	public int getFreq()
	{
		return freq;
	}
	
	public byte getValue()
	{
		return value;
	}
	
	public void setFreq(int freq)
	{
		this.freq = freq;
	}
	
	public void setValue(byte value)
	{
		this.value = value;
	}
	
	public HuffmanTreeNode getParent() 
	{
		return parent;
	}

	public void setParent(HuffmanTreeNode parent) 
	{
		this.parent = parent;
	}

	public HuffmanTreeNode getLeft() 
	{
		return left;
	}

	public void setLeft(HuffmanTreeNode left)
	{
		this.left = left;
	}

	public HuffmanTreeNode getRight() 
	{
		return right;
	}

	public void setRight(HuffmanTreeNode right) 
	{
		this.right = right;
	}

	//Here we implement Comparable
	//Used in the add() method of BinaryMinHeap
	@Override
	public int compareTo(HuffmanTreeNode Node) 
	{
		if(Node.getFreq() < this.freq)
			return -1;
		else if(Node.getFreq() > this.freq)
			return 1;
		return 0;
	}
}

