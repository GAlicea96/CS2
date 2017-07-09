import java.util.ArrayList;

public class AccountGrabber implements Subject
{
	private ArrayList<Observer>	observers; 
	private int subCount, videoCount, viewCount;
	
	public AccountGrabber()
	{
		observers = new ArrayList<Observer>();
	}
	
	@Override
	public void register(Observer newObserver) 
	{
		observers.add(newObserver);
	}

	@Override
	public void unregister(Observer deleteObserver) 
	{
		int observerIndex = observers.indexOf(deleteObserver);
		
		System.out.print("\nObserver " + deleteObserver.getObserverID() + " deleted\n");
		observers.remove(observerIndex);
	}

	@Override
	public void notifyObserver() 
	{
		for (Observer observer : observers)
		{
			observer.update(subCount,  videoCount, viewCount);
		}
		
	}
	
	public void setSubCount(int newSubCount)
	{
		this.subCount = newSubCount;
	}
	
	public void setVideoCount(int newVideoCount)
	{
		this.videoCount = newVideoCount;
	}
	
	public void setViewCount(int newViewCount)
	{
		this.viewCount = newViewCount;
		notifyObserver();
	}
	
	public Observer getObserver(int index)
	{
		return observers.get(index);
	}
	
	public int getObserverIndex(int ID)
	{
		for (int i = 0; i < observers.size(); i++)
		{
			if (observers.get(i).getObserverID() == ID)	
				return i;
		}
		return -1;
	}
}
