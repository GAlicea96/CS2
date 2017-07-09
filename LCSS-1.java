/*Guillermo Alicea
 * COP 3503C - 0013
 * Recitation Assignment 9 - LCSS
 * 04/05/16
 */

import java.util.*;
import java.io.*;

public class LCSS {

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OutputStreamWriter out = new OutputStreamWriter(System.out);
		StringTokenizer st1, st2;
		int numCases = Integer.parseInt(br.readLine());
		
		//Find the LCSS for n pairs of strings, where n = numCases
		for (int i = 0; i < numCases; i++)
		{
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			String[] string1 = new String[st1.countTokens() + 1], 
					string2 = new String[st2.countTokens() + 1];
			int[][] arr = new int[string2.length][string1.length];
			
			//Begin reading input..
			for (int j = 1; j < string1.length; j++)
				string1[j] = st1.nextToken();
			for (int j = 1; j < string2.length; j++)
				string2[j] = st2.nextToken();
			for (int j = 0; j < string2.length; j++)
				for (int k = 0; k < string1.length; k++)
					arr[j][k] = 0;
			//..input reading finishes.
			
			//Begin finding LCSS..
			for (int j = 1; j < string2.length; j++)
			{
				for (int k = 1; k < string1.length; k++)
				{
					if (string2[j].equals(string1[k]))
						arr[j][k] = arr[j-1][k-1] + 1;
					else
						arr[j][k] = Math.max(arr[j-1][k], arr[j][k-1]);
				}		
			}
			//..length of LCSS found.
			
			out.write("LCSS Length = " + arr[string2.length - 1][string1.length - 1] + ". ");
			
			int j = string2.length - 1, k = string1.length - 1, index = 0,
					max = arr[string2.length - 1][string1.length - 1];
			String[] lcss = new String[arr[string2.length - 1][string1.length - 1]];
			
			//Based on the populated array of ints and our strings, find
			//the LCSS shared between them and, in reverse order, copy it into
			//our String lcss. Only finds one possible LCSS, without consideration
			//for the possibility that this LCSS might not be unique.
			while (true)
			{
				if (max == 0)
					break;
				else 
				{
					int temp = Math.max(arr[j-1][k-1], 
							Math.max(arr[j-1][k], arr[j][k-1]));
					if (temp != max)
						lcss[index++] = string1[k];
					if (temp == arr[j-1][k-1])
					{
						j--;
						k--;
					}
					else if (temp == arr[j-1][k])
						j--;
					else 
						k--;
					max = temp;
				}
			}
			
			//Output the LCSS that we found.
			out.write("LCSS = ");
			for (int l = lcss.length - 1; l >= 0; l--)
			{	
				out.write("" + lcss[l]);
				if (l != 0)
					out.write(" ");
				else
					out.write(".\n");
			}
			
		}
		
		br.close();
		out.close();
		
	}

}
