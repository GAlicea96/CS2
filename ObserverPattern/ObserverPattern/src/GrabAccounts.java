import java.util.Scanner;

public class GrabAccounts 
{
	
	static void printMenu()
	{
		System.out.print("\nMain Menu\n"
				+ "1. Create Account\n"
				+ "2. Toggle SleepMode on an account.\n"
				+ "3. Edit Youtube Account info.\n"
				+ "4. Delete Account\n"
				+ "5. Exit\n");
	}
	
	static void toggleSleepMode(AccountGrabber accountGrabber, Scanner in)
	{
		System.out.print("For which account would you like to toggle SleepMode?\n"
				+ "Enter ObserverID: ");
		int id = in.nextInt(), index;
		
		//Assuming we are sleeping based on input of observerID, not index
		if ((index = accountGrabber.getObserverIndex(id)) != -1)
			accountGrabber.getObserver(index).toggleSleepMode();
		else
			System.out.println("Observer " + (id) + " is not in the list of observers.");
	}
	
	static void editInfo(AccountGrabber accountGrabber, Scanner in)
	{
		System.out.print("Please enter the new Subscriber Count.\n");
		accountGrabber.setSubCount(in.nextInt());
		System.out.print("Please enter the new Video Count.\n");
		accountGrabber.setVideoCount(in.nextInt());
		System.out.print("Please enter the new Viewer Count.\n");
		accountGrabber.setViewCount(in.nextInt());
		//Users are updated only once per call to editInfo,
		//similar to the .txt document
		System.out.print("\nAll Users Updated and Notified Accordingly.\n");
	}
	
	static void deleteAccount(AccountGrabber accountGrabber, Scanner in)
	{
		System.out.print("Which account would you like to delete?\n"
				+ "Enter ObserverID: ");
		int id = in.nextInt(), index;
		
		//Assuming we are deleting based on input of observerID, not index
		if ((index = accountGrabber.getObserverIndex(id)) != -1)
			accountGrabber.unregister(accountGrabber.getObserver(index));
		else
			System.out.println("Observer " + (id) + " is not in the list of observers.");
	}

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		AccountGrabber accountGrabber = new AccountGrabber();
		int input = 0;
		
		printMenu();
		
		while ((input = in.nextInt()) != 5)
		{
			if (input == 1)
				new AccountObserver(accountGrabber);
			else if (input == 2)
				toggleSleepMode(accountGrabber, in);
			else if (input == 3)
				editInfo(accountGrabber, in);
			else if (input == 4)
				deleteAccount( accountGrabber, in);
			//printMenu();
			//I'm not sure if we were supposed to repeatedly print the menu or not,
			//so i chose not to since it would probably be easier for testing if it wasn't
		}
	}

}
