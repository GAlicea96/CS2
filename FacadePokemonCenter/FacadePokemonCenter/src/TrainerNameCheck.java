
public class TrainerNameCheck {
	
	private String trainerName = "Ash Ketchum";
	//Please do not change this value.
	//I will be using this name to test your code. 
	
	public String getTrainerName()
	{
		//This seems self-explanatory.
		return trainerName;
	}
	
	public boolean trainerActive(String trainerNameToCheck)
	{
		//This function will check the given string against trainerName.
		//Return true if they are the same, false if they are not.
		
		//Make sure you use .equals() !
		return (trainerNameToCheck.equals(trainerName));
	}
	
		

}
