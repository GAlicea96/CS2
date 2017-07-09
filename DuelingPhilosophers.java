import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
/*Guillermo Alicea
 * COP 3503C - 0013
 * Recitation Assignment 4 - Dueling Philosophers
 * 02/25/16
 */

public class DuelingPhilosophers 
{
	//Here we read in the limits for our individual cases, call the function which
	//will create our adjacency list(s), and finally call the function that will decide
	//how many solutions are possible, if any, while also printing out this result.
	static private void solveProblem(String fileName) throws Exception
	{	
		Scanner input = new Scanner(new File(fileName));
		int numEssays, numLinks;
		LinkedList<ArrayList<Integer>> adjacencyList = null;
		
		while(true)
		{
			numEssays = input.nextInt();
			if(numEssays == 0)
				break;
				
			numLinks = input.nextInt();
			
			adjacencyList = new LinkedList<ArrayList<Integer>>();
			adjacencyList = makeSingleCaseList(numEssays, numLinks, input, adjacencyList);
			
			System.out.println(findNumberOfSolutions(numEssays, adjacencyList));
		}
		
			input.close();
	}
	
	//Create a new ArrayList for each essay and populate this ArrayList with whichever essays each particular essay points to.
	//Add a 0 at the end of every essay's list, as a terminator (essays that point to no tother essay will simply have a 0 in their list).
	static private LinkedList<ArrayList<Integer>> makeSingleCaseList(int numEssays, int numLinks, Scanner input, LinkedList<ArrayList<Integer>> adjacencyList)
	{	
		
			int n, m;
			for(int i = 0; i <= numEssays; i++)
				adjacencyList.add(i, new ArrayList<Integer>());
				
			for(int i = 1; i <= numLinks; i++)
			{	
				n = input.nextInt();
				m = input.nextInt();
				adjacencyList.get(n).add(m);
			}
			
			for(int i = 1; i <= numEssays; i++)
				adjacencyList.get(i).add(0);
			
		return adjacencyList;
	}
	
	//Create a copy of our adjacencyList and initialize an int array with the number of essays pointing to each of our individual essays.
	//Use this to "sort" the collection of essays, returning a 0 if a cycle is found (essays left to be added into our queue, but none of these essays
	//have a source count of 0), 1 if only one solution is found, and a 2 otherwise (multiple solutions exist).
	static private int findNumberOfSolutions(int numEssays, LinkedList<ArrayList<Integer>> adjacencyList)
	{
		int[] numSources = new int[numEssays + 1];
		int count = 0;
		boolean notInCycle = false;
		Integer currentEssay = 0;
		Queue<Integer> solution = new LinkedList<Integer>();
		LinkedList<ArrayList<Integer>> copy = new LinkedList<ArrayList<Integer>>();
		
		copy.add(0, new ArrayList<Integer>());
		for(int i = 1; i <= numEssays; i++)
		{
			copy.add(i, new ArrayList<Integer>());
			for(int j = 0; j < adjacencyList.get(i).size(); j++)
				copy.get(i).add(j, adjacencyList.get(i).get(j));
		}
			
		for(int i = 1; i <= numEssays; i++)
		{	
			numSources[i] = 0;
		}
		
		for(int i = 1; i <= numEssays; i++)
			for(int j = 0; adjacencyList.get(i).get(j) != 0; j++)
				numSources[adjacencyList.get(i).get(j)]++;
		
		while(true)
		{	
			notInCycle = false;
			for(int i = 1; i <= numEssays; i++)
			{
				
				if(numSources[i] == 0)
				{	
					numSources[i]--;
					solution.add(i);
					notInCycle = true;
					for(int j = 0; adjacencyList.get(i).get(j) != 0; j++)
					{	
						numSources[adjacencyList.get(i).get(j)]--;
						copy.get(i).remove(j);
					}
				}
			}
			if(solution.size() == numEssays)
				break;
			if(!notInCycle)
				return 0;
		}
		
		//This serves as our check whether or not only one solution exists.
		//If count is ever less than the number of elements pointed to by each particular essay,
		//then we know that the current essay does not point to the essay next in the queue,
		//indicating that more than one solutions exist.
		for(int i = 1; i <= numEssays; i++)
		{ 
			currentEssay = solution.peek();
			solution.remove();
			if(solution.peek() == null)
				break;
			count = 0;
			for(int j = 0; j < adjacencyList.get(currentEssay).size() - 1; j++)
			{	
				if(adjacencyList.get(currentEssay).get(j) != solution.peek())
					count++;
			}
			
			if(count == adjacencyList.get(currentEssay).size() - 1)
				return 2;
		}
		
		return 1;
	} 
	
	public static void main(String[] args) throws Exception 
	{
		//Assuming the input file will have the name "input.txt"
		solveProblem("input.txt");
	}

}
