
public class LinearHash 
{
	static int m_nTableSize = 10000, numElements = 0;
	static DataObject[] m_ObjectArray;
	
	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	//Added an expansion check based on the number of populated elements 
	//currently in the hash table
	public static void put( String strKey, DataObject objData )
	{
		if(numElements >= m_nTableSize/2)
			expand();
		
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null)
		{
			lHash++;
		}
		
		numElements++;
		m_ObjectArray[(int)lHash % m_nTableSize] = objData;
	}

	//Check was put in place to prevent throwing an exception;
	//instead null will be returned if a string is not found
	//in the hash table.
	public DataObject get( String strKey )
	{	
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null &&
				m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != strKey)
		{	
			lHash++;
			if(m_ObjectArray[(int)(lHash%m_nTableSize)] == null) return null;
		}
				
		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
	
	//Expands the hash table by 2 * its current table size.
	//Copy everything then "reput()" them into the new table.
	static void expand()
	{
		DataObject[] copy = m_ObjectArray;
		m_ObjectArray = new DataObject[m_nTableSize *= 2];
		
		numElements = 0;
		for(DataObject i : copy)
			if(i != null) 
				put( i.GetKey(), i);
	}
}
