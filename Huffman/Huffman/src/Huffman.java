import java.util.*;
import java.io.*;

public class Huffman 
{
	static byte[] fileData;
	static Hashtable<String, String> compressionTable;
	static int[] freqTable = new int[256];
	
	//Calculate frequency of each char (ignoring any nul/(char)0)
	static void calculateFreqs()
	{
		for(int i = 0; i < fileData.length; i++)
			if(fileData[i] != 0)
				freqTable[(int)fileData[i]]++;
	}
	
	//Read all the input
	static void readInput(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String buffer;
		fileData = new byte[countChars(fileName)];
		int index = 0;
		
		while((buffer = br.readLine()) != null)
		{	
			for(int j = 0; j < buffer.length(); index++, j++)
			{
				fileData[index] = (byte) buffer.charAt(j);
			}
		}
		
		br.close();
	}
	
	//readInput helper
	static int countChars(String fileName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		int count = 0;
		while(br.read() != -1)
			count++;
		br.close();
		return count;
	}
	
	//Make our initial Binary Heap, then make our HuffmanTree from this Heap
	//and finally make our compression table from this tree
	static void makeHuffmanCodes()
	{
		HuffmanTree tree = new HuffmanTree(new BinaryMinHeap(fileData, freqTable));
		compressionTable = new Hashtable<String, String>();
		tree.makeCompressionTable(tree.getRootNode(), "", compressionTable);
	}

	public static void main(String[] args) throws IOException 
	{
		//To output compressed file size
		int sum = 0;
		
		//Read Input and calculate frequency table
		readInput("input.dat");
		calculateFreqs();

		//Begin compressing file
		makeHuffmanCodes();
		
		//Output our compression results
		for(int i = 0; i < freqTable.length; i++)
		{
			
			if(freqTable[i] != 0)
			{	
				System.out.print("'"+(char) i +"' ");
				System.out.print(compressionTable.get(String.valueOf(i)) + "\n");
				sum += freqTable[i]*((compressionTable.get(String.valueOf(i)).length()));
			}
		}
		System.out.println("\nSize of compressed file: " + sum);
	}
}
