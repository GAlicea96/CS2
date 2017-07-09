import java.util.*;

public class SingletonDM 
{
	//Here, put the specific private static variable that makes this class follow the singleton pattern.
	private static SingletonDM singleton = null;
	private static boolean firstThread = true;
	//The array of randomized character sheets.
	//Feel free to hardcode a few of these for your testing.
	private PlayerCharacter sheets[] = 
	{
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(),
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter()
	};
	
	//The sheets array remade as a list for convenience.
	private LinkedList<PlayerCharacter> sheetList = new LinkedList<PlayerCharacter>(Arrays.asList(sheets));
	
	//Here, put the specific kind of constructor that makes this class follow the singleton pattern.
	private SingletonDM(){}
	
	public static synchronized SingletonDM getInstance()
	{
		//This will act as the "true" constructor for this class.
		//Its details should include but not be limited to the following:
		//		- Check if this is the first thread.
		//		- Check the private static variable at the top of the class.
		//		- Have the "synchronized" key word in there somewhere.
		//		- Return some kind of SingletonDM
		if(singleton == null)
		{
			if(firstThread)
			{
				firstThread = false;
				
				try
				{
					Thread.currentThread();
					Thread.sleep(1000);
				}
				catch(Exception el){}
			}
			
			synchronized(SingletonDM.class)
			{
				if(singleton == null)
					singleton = new SingletonDM();
			}

		}
		
		return singleton;
	}
	
	public String GetNameAndPID()
	{
		return( "Alicea, Guillermo, g3626806");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
	
	public LinkedList<PlayerCharacter> getSheetList()
	{
		//Pretty self-explanatory.
		return sheetList;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfLevel(int level)
	{
		//This should find all characters of a certain level in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
		LinkedList<PlayerCharacter> levelList = new LinkedList<PlayerCharacter>();
		for(int i = 0; i < sheetList.size(); i++)
			if(sheetList.get(i).getLevel() == level)
				levelList.add(sheetList.get(i));
		return levelList;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfType(String type)
	{
		//This should find all characters of a certain type in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
		LinkedList<PlayerCharacter> levelList = new LinkedList<PlayerCharacter>();
		for(int i = 0; i < sheetList.size(); i++)
			if(sheetList.get(i).gettype().equals(type))
				levelList.add(sheetList.get(i));
		return levelList;
	}
}
