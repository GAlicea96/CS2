
public class Factory 
{
	//incremented based on which vehicle is created
	private int numAirplanes = 0, numCars = 0,
			numBoats = 0;
	
	//make a vehicle based on the user's input
	public Vehicle makeVehicle(String newVehicleName)
	{	
		if(newVehicleName.equals("A"))
		{
			numAirplanes++;
			return new Airplane();
		}
		
		else if(newVehicleName.equals("C"))
		{
			numCars++;
			return new Car();
		}
		
		else if (newVehicleName.equals("B"))
		{
			numBoats++;
			return new Boat();
		}
			
			return null;

	}
	//Print our report based on the components of each vehicle
	//and the number of vehicles created
	public void printReport(Vehicle airplane, Vehicle car, Vehicle boat)
	{
		if(airplane != null)
		{	
			System.out.println("Number of Airplanes: " + numAirplanes);
			System.out.println("Number of Airplane wheels needed: " + airplane.getNumWheels()*numAirplanes);
			System.out.println("Number of Airplane engines needed: " + airplane.getNumEngines()*numAirplanes);
		}
		else
		{	
			System.out.println("Number of Airplanes: 0");
			System.out.println("Number of Airplane wheels needed: 0");
			System.out.println("Number of Airplane engines needed: 0");	
		}
		if(car != null)
		{
			System.out.println("Number of Cars: " + numCars);
			System.out.println("Number of Car wheels needed: " + car.getNumWheels()*numCars);
			System.out.println("Number of Car engines needed: " + car.getNumEngines()*numCars);
		}
		else
		{
			System.out.println("Number of Cars: 0");
			System.out.println("Number of Car wheels needed: 0");
			System.out.println("Number of Car engines needed: 0");	
		}
		if(boat != null)
		{	
			System.out.println("Number of Boats: " + numBoats);
			System.out.println("Number of Boat wheels needed: " + boat.getNumWheels()*numBoats);
			System.out.println("Number of Boat engines needed: " + boat.getNumEngines()*numBoats);
		}
		else
		{
			System.out.println("Number of Boats: 0");
			System.out.println("Number of Boat wheels needed: 0");
			System.out.println("Number of BoSat engines needed: 0");	
		}
	}

}
