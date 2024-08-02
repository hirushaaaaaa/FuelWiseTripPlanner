import java.util.*;

public class FuelWiseTripPlanner {
     public static void main(Scanner[] arg) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter fuel consumption rate (km/l): ");
        double fuelConsumptionRate = scanner.nextDouble();

        System.out.println("Enter fuel tank capacity (liters): ");
        double fuelTankCapacity = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(fuelConsumptionRate, fuelTankCapacity);

        Queue<TripRequest> tripQueue = new LinkedList<>();

        while(true){

            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate Distance Possible");
            System.out.println("2. Calculate Fuel Needed for Distance");
            System.out.println("3. Calculate Fuel Consumption Rate");
            System.out.println("4. Convert Miles to Kilometers");
            System.out.println("5. Convert Kilometers to Miles");
            System.out.println("6. Estimate Cost of Trip");
            System.out.println("7. Check if your input is valid");
            System.out.println("8. Calculate Average Speed");
            System.out.println("9. Find Cheapest Fuel Station");
            System.out.println("10. Add Trip Request to Queue");
            System.out.println("11. Process Trip Request Queue");
            System.out.println("12. Exit");

            System.out.print("Enter your choice (1-12): ");
            int choice = scanner.nextInt();

            if (choice ==12){
                System.out.println("Exiting....");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount of fuel (liters): ");
                    double fuelAmount = scanner.nextDouble();
                    double distancePossible = vehicle.calculateDistancePossible(fuelAmount);
                    System.out.println("Distance Possible: " +distancePossible + " kilometers");
                    break;

                case 2:
                    System.out.print("Enter distance (km): ");
                    double distanceForFuel = scanner.nextDouble();
                    double fuelNeeded = vehicle.calculateFuelNeeded(distanceForFuel);
                    System.out.println("Fuel Needed: " + fuelNeeded + " liters");
                    break;        
            }
        }
     }
}
