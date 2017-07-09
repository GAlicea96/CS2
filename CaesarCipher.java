/*Guillermo Alicea
 * COP 3503C - 0013
 * Caesar Cipher
 * 03/15/16
 */

public class CaesarCipher {
	
	//expected values of our letters' frequencies
	static double[] table = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 
			7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 
			9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	//char to ascii value of char
	static int let2nat(char c)
	{
		return c - 97;
	}
	
	//ascii value of char to its respective char
	static char nat2let(int code)
	{
		return (char)(code + 97);
	}
	
	//shifts a letter to the right an amount equal to shiftAmt
	static char shift(int shiftAmt, char c)
	{
		return c >= 97 && c <= 122 ? nat2let((26 + let2nat(c) + shiftAmt)%26) : c;	
	}
	
	//encodes a string by shifting its chars to the right 
	//by an amount equal to shiftAmt
	static String encode(int shiftAmt, String str)
	{
		String encodedStr = "";
		
		for(int i = 0; i < str.length(); i++)
			encodedStr += shift(shiftAmt, str.charAt(i));

		return encodedStr;
	}
	
	//decodes a string by shifting its char to the left
	//by an amount equal to shiftAmt
	static String decode(int shiftAmt, String str)
	{
		String encodedStr = "";
		
		for(int i = 0; i < str.length(); i++)
			encodedStr += shift(-shiftAmt, str.charAt(i));

		return encodedStr;
	}
	
	//counts the number of lower case letters in a string
	//(ascii values between 97 and 122, inclusive)
	static int lowers(String str)
	{
		int count = 0;
		
		for(int i = 0; i < str.length(); i++)
			if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
				count++;
		
		return count;
	}
	
	//counts the number of times a specific char is encountered in a string
	static int count(char c, String str)
	{
		int count = 0;
		
		for(int i = 0; i < str.length(); i++)
			if(str.charAt(i) == c)
				count++;
		
		return count;
	}
	
	//percentage of one int with respect to another
	static double percent(int num1, int num2)
	{
		return 100 * (double)num1/num2;
	}
	
	//computes the frequency of occurrence of each letter in the alphabet with
	//respect to the total number of lower case letters in the string
	static double[] freqs(String str)
	{
		double[] freq = new double[26];
		int numLowers = lowers(str);
		
		for(int i = 0; i < str.length(); i++)
			if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
				freq[let2nat(str.charAt(i))] = 
					percent(count(str.charAt(i), str), numLowers);
		
		return freq;
	}
	
	//shifts all the values in the array to the left by an amount equal to n
	static double[] rotate(int n, double[] list)
	{
		double[] newList = new double[list.length];
		
		for(int i = 0; i < list.length; i++)
			newList[i] = list[(list.length + i + n)%list.length];
	
		return newList;
	}
	
	//computes the sum of (os-es)^2/es from 0 to os.length - 1
	static double chisqr(double[] os)
	{
		double value = 0;
		
		for(int i = 0; i < os.length; i++)
			value += ((os[i] - table[i])*(os[i] - table[i]))/(table[i]);
	
		return value;
	}
	
	//returns the index that contains the double a in the array of doubles list[]
	static int position(double a, double[] list)
	{
		for(int i = 0; i < list.length; i++)
			if(list[i] == a) return i;
		
		return 0;
	}
	
	//calculates the frequency of each lower case letter in the string,
	//the chisqr of these frequencies rotated from 0 to 25, and finally decodes
	//the string with a shiftAmt equal to the number of rotations that most closely
	//relates to our expected frequencies.
	static String crack(String str)
	{
		double[] freqStr = freqs(str);
		double[] chiSqr = new double[26];
		double min = 0;
		
		for(int i = 0; i < 26; i++)
			chiSqr[i] = chisqr(rotate(i, freqStr));
		
		min = chiSqr[0];
		
		for(int i = 1; i < 26; i++)
			if(chiSqr[i] < min) min = chiSqr[i];
		
		return decode(position(min, chiSqr), str);
	}
	
	public static void main(String[] args) 
	{
		System.out.println("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!");
		System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
	}

}
