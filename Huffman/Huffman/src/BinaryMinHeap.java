public class BinaryMinHeap
{
	//Heap is made up of HuffmanTreeNodes which support 
	//generics and implement Comparable
	private static int size = 0; 
	private static int insertionPoint = 0; 
	private static HuffmanTreeNode[] minHeap;
	
	//Constructor creates our initial Heap
	public BinaryMinHeap(byte[] fileData, int[] freqTable)
	{
		int initSize = 0;
		for(int i = 0; i < freqTable.length; i++)
		{	
			if(freqTable[i] != 0)
				initSize++;
		}
		minHeap = new HuffmanTreeNode[initSize];
		for(int i = 0; i < freqTable.length; i++)
		{
			if(freqTable[i] != 0)
				add(new HuffmanTreeNode(freqTable[i], (byte) i));
		}
	}
	
	/*public void printHeap()
	{
		for(int i = 0; i < size; i++)
			System.out.println(minHeap[i].getFreq());
		System.out.println("-------------");
	}*/
	
	//Add to Heap
	public void add(HuffmanTreeNode x)
	{
		int i = insertionPoint++;
		for(; 
				i > 0 && x.compareTo(minHeap[i-1]) > 0; i--)
			minHeap[i] = minHeap[i-1];
		minHeap[i] = x;
		size++;
		
	}

	//Pop from Heap
	public HuffmanTreeNode pop()
	{
		HuffmanTreeNode keeper = minHeap[0];
		for(int i = 0; i < size-1; i++)
			minHeap[i] = minHeap[i+1];
		size--;
		insertionPoint--;
		return keeper;
	}

	//get size of heap
	public int size()
	{
		return size;
	}
	
	//get minHeap of this instantiation
	public HuffmanTreeNode[] getMinHeap()
	{
		return minHeap;
	}
}
