
public class FacadePokemonCenter 
{
	//Declare a private int and a private String up here for the trainer's ID and name.
	private int trainerID;
	private String trainerName;
	
	//Also, be sure to declare variables of the following type:
	//		- TrainerIDCheck
	//		- TrainerNameCheck
	//		- HealAndPC
	//		- Welcome
	//Don't instantiate them yet - that can happen in the constructor.
	TrainerIDCheck trainerIdCheck;
	TrainerNameCheck trainerNameCheck;
	HealAndPC healAndPc;
	Welcome welcome;
	
	public FacadePokemonCenter(int newID, String newName)
	{
		//Here in the constructor, set the variables based on the input.
		trainerID = newID;
		trainerName = newName;
		
		//Also, instantiate the four classes here.
		trainerIdCheck = new TrainerIDCheck();
		trainerNameCheck = new TrainerNameCheck();
		healAndPc = new HealAndPC();
		welcome = new Welcome();
		
	}
	
	public int getTrainerID()
	{
		//Pretty self-explanatory.
		return trainerID;
	}
	
	public String getTrainerName()
	{
		//Also self-explanatory.
		return trainerName;
	}
	
	public void healPokemon()
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//If every case is true, call the heal function in your instance of HealAndPC.
		//		In addition, print that the healing was successful.
		//Otherwise, do nothing, and say why the healing was not done.
		//Make sure you print out a statement on whether healing was successful or not!
		//This is crucial to how we will grade your assignment
		boolean idCheck = false, nameCheck = false, emptyCheck = false;
		
		idCheck = trainerIdCheck.trainerActive(trainerID);
		nameCheck = trainerNameCheck.trainerActive(trainerName);
		emptyCheck = !healAndPc.isTeamEmpty();
		
		
		if (idCheck && nameCheck && emptyCheck)
		{
			healAndPc.healPokemon();
			System.out.print("Healing Succesful:\n"
					+ "I'll take your Pokemon for a few seconds...\n"
					+ "Thank you for waiting. We've restored your Pokemon to full health.\n"
					+ "We hope to see you again!\n");
		}
		else
		{
			System.out.print("Healing unsuccessful:\n");
			if (!idCheck)
				System.out.print("IDCheck failed\n");
			if (!nameCheck)
				System.out.print("NameCheck failed\n");
			if (!emptyCheck)
				System.out.print("Team is empty\n");
		}
		System.out.print("\n");	
	}
	
	public void depositPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//		- Check if the team contains the given Pokemon.
		//If every case is true, call the deposit function in your instance of HealAndPC.
		//		In addition, print that the deposit was successful.
		//Otherwise, do nothing, and say why the deposit was not made.
		//Make sure you print out a statement on whether the deposit was successful or not!
		//This is crucial to how we will grade your assignment
		
		boolean idCheck = false, nameCheck = false, emptyCheck = false,
				validPokemon = false, pokemonInTeam = false;
		
		idCheck = trainerIdCheck.trainerActive(trainerID);
		nameCheck = trainerNameCheck.trainerActive(trainerName);
		emptyCheck = !healAndPc.isTeamEmpty();
		validPokemon = (dexNum < 722 && dexNum >= 0);
		pokemonInTeam = healAndPc.containsPokemon(dexNum);
		
		if (idCheck && nameCheck && emptyCheck && validPokemon && pokemonInTeam)
		{
			healAndPc.depositPokemon(dexNum);
			System.out.print("Deposit succesful:\n"
					+ "Thank you for waiting. We've deposited your Pokemon to your PC.\n"
					+ "We hope to see you again!\n");
		}
		else
		{
			System.out.print("Deposit unsuccessful:\n");
			if (!idCheck)
				System.out.print("IDCheck failed\n");
			if (!nameCheck)
				System.out.print("NameCheck failed\n");
			if (!emptyCheck)
				System.out.print("Team is empty\n");
			if (!validPokemon)
				System.out.print("Not a valid Pokemon\n");
			if (!pokemonInTeam)
				System.out.print("Pokemon is not in your team\n");
		}
		System.out.print("\n");
	}
	
	public void withdrawPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has space for another Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//If every case is true, call the withdrawal function in your instance of HealAndPC.
		//		In addition, print that the withdrawal was successful.
		//Otherwise, do nothing, and say why the withdrawal was not made.
		//Make sure you print out a statement on whether withdrawing was successful or not!
		//This is crucial to how we will grade your assignment
		
		boolean idCheck = false, nameCheck = false, fullCheck = false, validPokemon = false;
		
		idCheck = trainerIdCheck.trainerActive(trainerID);
		nameCheck = trainerNameCheck.trainerActive(trainerName);
		fullCheck = !healAndPc.isTeamFull();
		validPokemon = (dexNum < 722 && dexNum >= 0);
		
		if (idCheck && nameCheck && fullCheck && validPokemon)
		{
			healAndPc.withdrawPokemon(dexNum);
			System.out.print("Withdrawal succesful:\n"
					+ "Thank you for waiting. We've withdrawn your Pokemon from your PC.\n"
					+ "We hope to see you again!\n");
		}
		else
		{
			System.out.print("Withdrawal unsuccessful:\n");
			if (!idCheck)
				System.out.print("IDCheck failed\n");
			if (!nameCheck)
				System.out.print("NameCheck failed\n");
			if (!fullCheck)
				System.out.print("Team is full\n");
			if (!validPokemon)
				System.out.print("Not a Valid pokemon\n");
		}
		System.out.print("\n");
	}
	
	public void printTeamAndHealth()
	{
		//This should just call printTeamAndHealth() in your instance of HealAndPC.
		healAndPc.printTeamAndHealth();
	}
	

}
