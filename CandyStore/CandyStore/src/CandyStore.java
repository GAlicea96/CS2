/*Guillermo Alicea
 * COP 3503C - 0013
 * Recitation Assignment 8 - CandyStore
 * 03/29/16
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CandyStore {
	
	//Reads in the input in "input.txt" and computes the maximum amount of calories
	//by populating an array of size totalMoney + 1 (+1 to ensure that the possibility
	// of using up all of our money is accounted for)(size could potentially end up being
	//100,000) with the maximums between the calories accounting for the current price
	//and the calories not accounting for the current price.
	static void findMaxCalories(String filename) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String s1, s2;
		StringTokenizer st;
		int numCandies = 0;
		int totalMoney = 0;
		
		s1 = br.readLine();
		st = new StringTokenizer(s1);
		while ((numCandies = Integer.parseInt(st.nextToken())) != 0
				&& (totalMoney = (int)(Float.parseFloat(st.nextToken()) * 100 + 0.01)) != 0)
		{	
			//Length will be our safeguard against spending too much money.
			int[] calories = new int[totalMoney + 1];
			
			//~O(k*n), where k = totalMoney and n = numCandies
			for (int i = 0; i < numCandies; i++) 
			{
				s2 = br.readLine();
				st = new StringTokenizer(s2);
				int iCalories = Integer.parseInt(st.nextToken());
				//+ 0.01 accounts for problems when casting to int
				int iPrice = (int)(Float.parseFloat(st.nextToken()) * 100 + 0.01);
				
				//Going from each candy's price up to the maximum that we can spend.
				//Change the value as we go along if we find a combination that contains more 
				//calories if we include this candy. We are essentially incrementing by pennies.
				for (int j = iPrice; j < calories.length; j++)
					calories[j] = (calories[j] >= calories[j - iPrice] + iCalories) ? 
							calories[j] : calories[j - iPrice] + iCalories;
				
			}
			
			System.out.println(calories[calories.length - 1]);
			s1 = br.readLine();
			st = new StringTokenizer(s1);
		}
		
		br.close();
	}
		
	public static void main(String[] args) throws IOException 
	{
		findMaxCalories("input.txt");
	}

}


