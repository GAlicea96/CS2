
public interface Observer 
{
	public void update(int subCount, int videoCount, int viewCount);
	//to toggle sleep mode
	public void toggleSleepMode();
	//to find our observer
	public int getObserverID();
}
