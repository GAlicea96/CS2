import java.util.Hashtable;

public class HuffmanTree 
{
	private HuffmanTreeNode rootNode;
	private int freqSize = 0;
	private int maxLength = 0;
	
	public HuffmanTreeNode getRootNode() {
		return rootNode;
	}

	public int getFreqSize() {
		return freqSize;
	}

	public int getMaxLength() {
		return maxLength;
	}

	//Constructor creates our HuffmanTree based on a given minHeap
	//Follows very closely with the algorithm shown in class
	public HuffmanTree(BinaryMinHeap minHeap)
	{ 
		freqSize = minHeap.size();
		while(minHeap.size() > 1)
		{
			HuffmanTreeNode popped1, popped2;
			popped1 = minHeap.pop();
			HuffmanTreeNode min1 = popped1;
			popped2 = minHeap.pop();
			HuffmanTreeNode min2 = popped2;
			HuffmanTreeNode combined = new
							HuffmanTreeNode(popped1.getFreq(),
							popped2.getFreq());
			combined.setLeft(min1);
			combined.setRight(min2);
			min1.setParent(combined);
			min2.setParent(combined);
			minHeap.add(combined);
			rootNode = combined;
		}
	}
	
	//Create Compression Table based on HuffmanTree
	//Essentially an in-order traversal
	public void makeCompressionTable(HuffmanTreeNode root, String out, Hashtable<String, String> compressionTable)
	{
		if(root!=null)
		{
			makeCompressionTable(root.getLeft(), out + "0", compressionTable);
			if(out.length() > maxLength)	maxLength = out.length();
			compressionTable.put(String.valueOf(root.getValue()), out);
			makeCompressionTable(root.getRight(), out + "1", compressionTable);
		}
	}
}
