import java.util.ArrayList;

public class SeparateChainingHashTable 
{
	int m_nTableSize = 10000;
	@SuppressWarnings("unchecked")
	//implementation using ArrayList insead of LinkedList
	//Expansion not used since an ArrayList can grow dynamically
	ArrayList<DataObject>[] m_ObjectArray = new ArrayList[10000];
	
	public SeparateChainingHashTable()
	{
	}
	
	public SeparateChainingHashTable(int nTableSize)
	{
		m_nTableSize = nTableSize;
	}
	
	//Find index; if null, make a new ArrayList, otherwise
	//add onto the one already made (when that ArrayList was originally
	//created from a null index)
	public void put( String strKey, DataObject objData )
	{	
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		if( m_ObjectArray[(int)(lHash%m_nTableSize)] == null)
			m_ObjectArray[(int)(lHash % m_nTableSize)] = 
					new ArrayList<DataObject>();
		
		m_ObjectArray[(int)(lHash % m_nTableSize)].add(objData);
	}

	//Handles the case of a string not being in the table, in which
	//case null will be returned. Goes to index; if null, returns null,
	//otherwise, goes down the list until string is found.
	public DataObject get( String strKey )
	{	
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		if(m_ObjectArray[(int)(lHash % m_nTableSize)] == null) return null;
		
		for(int i = 0; i < m_ObjectArray[(int)(lHash % m_nTableSize)].size(); i++ )
			if(m_ObjectArray[(int)(lHash % m_nTableSize)].get(i).GetKey().equals(strKey))
				return m_ObjectArray[(int)(lHash % m_nTableSize)].get(i);
		return null;
	}
}
