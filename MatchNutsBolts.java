/*Guillermo Alicea
 * COP 3503C - 0013
 * Recitation Assignment 2 - Nuts and Bolts
 * 01/27/16
 */
public class MatchNutsBolts {

	// Pre-condition: nuts and bolts are of the same size and		
	//      contain Nut and Bolt objects that all
	//      match, but not necessarily in the given	
	//      order.
	//Post-condition: nuts and bolts will store matching Nut
	//       and Bolt objects in each index. Thus,
	//       the Nut in index 0 of nuts will match
	//       the Bolt in index 0 of bolts, etc.

	public static void match(Nut[] nuts, Bolt[] bolts)
	{
		quickSort(nuts, bolts, 0, bolts.length - 1);
	}
		//Divide and conquer by implementing a tweak to quickSort in order
		//to match the nuts with the bolts. Template follows quickSort, but uses
		//an element from the other array in order to partition the arrays, i.e.
		//bolts partitioned with an element from nuts, and vice versa
		static void quickSort(Nut[] nuts, Bolt[] bolts, int lo, int hi)
		{
			if(lo < hi)
			{
				int pivot = partition(nuts, lo, hi, bolts[hi]);
				partition(bolts, lo, hi, nuts[pivot]);
				quickSort(nuts, bolts, lo, pivot - 1);
				quickSort(nuts, bolts, pivot + 1, hi);
			}
		}
		//Partition the nuts
		static int partition(Nut[] nuts, int lo, int hi, Bolt pivot)
		{
			int i = lo;
			boolean end = false;
			for(int j = lo; j < hi; j++)
			{
				//if compareTo returns -1
				if(nuts[j].compareTo(pivot) < 0)
				{
					swap(nuts, i, j);
					i++;
					end = false;
				}
				//if compareTo returns 0 (finds a match)
				else if(nuts[j].compareTo(pivot) == 0)
				{
					swap(nuts, j, hi);
					if(end)
					{
						end = false;
						continue;
					}
					end = true;
					j--;
				}
				//if compareTo returns 1
				else
					end = false;
			}
			swap(nuts, i, hi);
			
			return i;
		}
		//Partition the bolts
		static int partition(Bolt[] bolts, int lo, int hi, Nut pivot)
		{
			int i = lo;
			boolean end = false;
			for(int j = lo; j < hi; j++)
			{	
				//if compareTo returns -1
				if(bolts[j].compareTo(pivot) < 0)
				{
					swap(bolts, i, j);
					i++;
					end = false;
				}
				//if compareTo returns 0 (finds a match)
				else if(bolts[j].compareTo(pivot) == 0)
				{
					swap(bolts, j, hi);
					if(end)
					{
						end = false;
						continue;
					}
					end = true;
					j--;
				}
				//if compareTo returns 1
				else
					end = false;
			}
			swap(bolts, i, hi);
			
			return i;
			
		}
		//swap function
		static void swap(Object[] array, int currentIndex, int newIndex)
		{
			Object temp = array[newIndex]; array[newIndex] = array[currentIndex]; array[currentIndex] = temp;
		}

	
	public static void main(String[] args) 
	{
	}

}
