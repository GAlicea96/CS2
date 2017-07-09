
public class AccountObserver implements Observer
{

	private int subCount;
	private int videoCount;
	private int viewCount;
	private boolean sleepMode = false;
	
	private static int observerIDTracker = 0;
	
	private int observerID;
	
	
	public AccountObserver(Subject accountGrabber)
	{
		
		this.observerID = ++observerIDTracker;
		
		System.out.print("\nNew Observer: ObserverID - " + this.observerID + "\n");
		
		accountGrabber.register(this);
	}
	
	@Override
	public void update(int subCount, int videoCount, int viewCount) 
	{
		this.subCount = subCount;
		this.videoCount = videoCount;
		this.viewCount = viewCount;
		
		if (!sleepMode)
			printStatistics();
	}
	
	public void toggleSleepMode()
	{
		sleepMode = !sleepMode;
	}
	
	public int getObserverID()
	{
		return observerID;
	}
	
	public void printStatistics()
	{
		System.out.print("\nObserverID - " + observerID + ":\n"
				+ "Subscriber Count: " + subCount + "\n"  
				+ "Video Count: " + videoCount + "\n" 
				+ "Viewer Count: " + viewCount + "\n");
	}
	
}
