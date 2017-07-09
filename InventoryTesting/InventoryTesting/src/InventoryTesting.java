/*Guillermo Alicea
 * COP 3503C - 0013
 * Factory
 * 03/28/16
 */
import java.util.Scanner;

public class InventoryTesting {

	public static void main(String[] args) 
	{
		String vehicleName;
		
		Factory factory = new Factory();
		Vehicle airplane = null, boat = null, car = null;
		
		Scanner input = new Scanner(System.in);
		
		//Begin reading in the user's input and either create a vehicle or print the report
		System.out.print("What would you like to do:\nAdd Airplane(A)\nAdd Boat (B)\n"
				+ "Add Car (C)\nPrint Report (R)\n");
		while(input.hasNextLine())
		{
			vehicleName = input.nextLine();
			if(vehicleName.equals("R"))
			{
				factory.printReport(airplane, car, boat);
				System.out.print("\nFinish Inventory Testing? (Y / N)\n");
				if(input.nextLine().equals("Y"))
				{
					System.out.print("Program Terminated.");
					break;
				}
			}
			else
			{
				if(vehicleName.equals("A"))
				{
					airplane = new Vehicle();
					airplane = factory.makeVehicle(vehicleName);
					System.out.print("Airplane added.\n");
				}
				else if(vehicleName.equals("B"))
				{
					boat = new Vehicle();
					boat = factory.makeVehicle(vehicleName);
					System.out.print("Boat added.\n");
				}
				else if(vehicleName.equals("C"))
				{
					car = new Vehicle();
					car = factory.makeVehicle(vehicleName);
					System.out.print("Car added.\n");
				}
				else if(vehicleName.equals("R"))
				{
					car = new Vehicle();
					car = factory.makeVehicle(vehicleName);
				}
				else
					System.out.print("Please enter A, B, C, or R next time.\n");
			}
			
			System.out.print("What would you like to do:\nAdd Airplane(A)\nAdd Boat (B)\n"
					+ "Add Car (C)\nPrint Report (R)\n");
		}
		
		input.close();
	}

}
