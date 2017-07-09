
public class QuadraticProbeHashTable 
{
	
	static int m_nTableSize = 10000, numElements = 0;
	static DataObject[] m_ObjectArray;
	//prime size should be dependent on the expected input.
	static boolean[] prime = new boolean[110000];
	
	public QuadraticProbeHashTable()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public QuadraticProbeHashTable(int nTableSize)
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	//check for expansion with each put. Unlike Linear probing
	//increment the offset rather than the hash value
	public void put( String strKey, DataObject objData )
	{
		if(numElements >= m_nTableSize/2)
			expand();
		
		int q = 1;
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		while( m_ObjectArray[(int)((lHash + (q * q)) % m_nTableSize)] != null)
		{
			q++;
		}
		
		numElements++;
		m_ObjectArray[(int)((lHash + q * q) % m_nTableSize)] = objData;
	}

	//return null if string not found; similar to linear probing
	//with the exception of offset increments, as opposed to
	//hash value increments.
	public DataObject get( String strKey )
	{	
		int q = 1;
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		while( m_ObjectArray[(int)((lHash + q * q) % m_nTableSize)] != null &&
				m_ObjectArray[(int)((lHash + q * q) % m_nTableSize)].GetKey() != strKey)
		{
			q++;
			if(m_ObjectArray[(int)((lHash + q * q) % m_nTableSize)] == null)
				return null;
		}

		return( m_ObjectArray[(int)((lHash + q * q) % m_nTableSize)] );
	}
	
	//Template credit given to T.A. who published Hash Table notes.
	//Finds the next prime number after table size * 2 and before
	//11000 (preset length of the prime array, but can be varied
	//depending on the expected input).
	static int nextPrime()
	{
		int i;
		
		for(int j = 0; j < prime.length; j++)
			prime[j] = true;
		
		for (int j = 2; j < Math.sqrt(prime.length); j++)
		{
			if (prime[j] == true)
			{
				for (int k = j*j; k < prime.length; k += j)
				{
					prime[k] = false;
				}
			}
		}
		
		for(i = m_nTableSize * 2; i < prime.length; i++)
			if(prime[i] == true) break;
		return i;
	}
	
	//Similar to linear probing expansion, except the condition
	//of finding a prime value for table size is added
	void expand()
	{
		DataObject[] copy = m_ObjectArray;
		m_ObjectArray = 
				new DataObject[m_nTableSize = nextPrime()];
		
		numElements = 0;
		for(DataObject i : copy)
			if(i != null)
				put( i.GetKey(), i);
	}
}


