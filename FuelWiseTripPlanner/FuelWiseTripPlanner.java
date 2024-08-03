import java.util.*;

public class FuelWiseTripPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create vehicle instance for calculations
        System.out.print("Enter fuel consumption rate (km/l): ");
        double fuelConsumptionRate = scanner.nextDouble();

        System.out.print("Enter fuel tank capacity (liters): ");
        double fuelTankCapacity = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(fuelConsumptionRate, fuelTankCapacity);

        Queue<TripRequest> tripQueue = new LinkedList<>();

        while (true) {
            // Display menu
            System.out.println("\nChoose any option:");
            System.out.println("1. Calculate Distance Possible");
            System.out.println("2. Calculate Fuel Needed for Distance");
            System.out.println("3. Calculate Fuel Consumption Rate");
            System.out.println("4. Convert Miles to Kilometers");
            System.out.println("5. Convert Kilometers to Miles");
            System.out.println("6. Estimate Cost of Trip");
            System.out.println("7. Check whether your input is valid");
            System.out.println("8. Calculate Average Speed");
            System.out.println("9. Find Cheapest Fuel Station");
            System.out.println("10. Add Trip Request to Queue");
            System.out.println("11. Display Trip Request Queue");
            System.out.println("12. Close");

            System.out.println();
            System.out.print("Enter your choice (1-12): ");
            int choice = scanner.nextInt();

            if (choice == 12) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount of fuel (liters): ");
                    double fuelAmount = scanner.nextDouble();
                    double distancePossible = vehicle.calculateDistancePossible(fuelAmount);
                    System.out.println("Distance Possible: " + distancePossible + " kilometers");
                    break;

                case 2:
                    System.out.print("Enter distance (km): ");
                    double distanceForFuel = scanner.nextDouble();
                    double fuelNeeded = vehicle.calculateFuelNeeded(distanceForFuel);
                    System.out.println("Fuel Needed: " + fuelNeeded + " liters");
                    break;

                case 3:
                    System.out.print("Enter distance traveled (km): ");
                    double distanceTraveled = scanner.nextDouble();
                    System.out.print("Enter fuel used (liters): ");
                    double fuelUsed = scanner.nextDouble();
                    double consumptionRate = distanceTraveled / fuelUsed;
                    System.out.println("Fuel Consumption Rate: " + consumptionRate + " km/l");
                    break;

                case 4:
                    System.out.print("Enter distance in miles: ");
                    double miles = scanner.nextDouble();
                    double kilometers = miles * 1.60934;
                    System.out.println(miles + " miles is " + kilometers + " kilometers");
                    break;

                case 5:
                    System.out.print("Enter distance in kilometers: ");
                    double kms = scanner.nextDouble();
                    double milesConverted = kms / 1.60934;
                    System.out.println(kms + " kilometers is " + milesConverted + " miles");
                    break;

                case 6:
                    System.out.print("Enter trip distance (km): ");
                    double tripDistance = scanner.nextDouble();
                    System.out.print("Enter fuel price (per liter): ");
                    double tripFuelPrice = scanner.nextDouble();
                    Trip trip = new Trip(tripDistance, tripFuelPrice, 0);
                    double tripCost = trip.estimateCost(vehicle.getFuelConsumptionRate());
                    System.out.println("Estimated Trip Cost: Rs" + tripCost);
                    break;

                case 7:
                    System.out.print("Enter a value to validate: ");
                    double value = scanner.nextDouble();
                    if (value > 0) {
                        System.out.println("Valid input.");
                    } else {
                        System.out.println("Invalid input. Please enter a positive value.");
                    }
                    break;

                case 8:
                    System.out.print("Enter trip distance (km): ");
                    double distance = scanner.nextDouble();
                    System.out.print("Enter trip time (hours): ");
                    double time = scanner.nextDouble();
                    trip = new Trip(distance, 0, time);
                    double averageSpeed = trip.calculateAverageSpeed();
                    System.out.println("Average Speed: " + averageSpeed + " km/h");
                    break;

                case 9:
                    // Input for Fuel Stations
                    System.out.print("Enter number of fuel stations: ");
                    int numberOfStations = scanner.nextInt();

                    List<FuelStation> stations = new ArrayList<>();
                    for (int i = 0; i < numberOfStations; i++) {
                        System.out.print("Enter name of fuel station " + (i + 1) + ": ");
                        String name = scanner.next();

                        System.out.print("Enter price of fuel at " + name + ": ");
                        double price = scanner.nextDouble();

                        stations.add(new FuelStation(name, price));
                    }

                    // Find cheapest station using PriorityQueue (min-heap)
                    PriorityQueue<FuelStation> pq = new PriorityQueue<>(Comparator.comparingDouble(FuelStation::getPrice));
                    pq.addAll(stations);

                    FuelStation cheapestStation = pq.poll();
                    if (cheapestStation != null) {
                        System.out.println("Cheapest Fuel Station: " + cheapestStation.getName() + " with price Rs" + cheapestStation.getPrice() + " per liter");
                    } else {
                        System.out.println("No stations available.");
                    }
                    break;

                case 10:
                    System.out.print("Enter trip distance (km): ");
                    double queueTripDistance = scanner.nextDouble();
                    System.out.print("Enter fuel price (per liter): ");
                    double queueFuelPrice = scanner.nextDouble();
                    System.out.print("Enter trip time (hours): ");
                    double queueTripTime = scanner.nextDouble();
                    tripQueue.add(new TripRequest(queueTripDistance, queueFuelPrice, queueTripTime));
                    System.out.println("Trip request added to queue.");
                    break;

                case 11:
                    if (tripQueue.isEmpty()) {
                        System.out.println("The trip request queue is empty.");
                    } else {
                        System.out.println("Processing trip request queue...");
                        System.out.println();
                        while (!tripQueue.isEmpty()) {
                            TripRequest request = tripQueue.poll();
                            trip = new Trip(request.getDistance(), request.getFuelPrice(), request.getTime());
                            tripCost = trip.estimateCost(vehicle.getFuelConsumptionRate());
                            averageSpeed = trip.calculateAverageSpeed();
                            System.out.println("Trip Distance: " + request.getDistance() + " km, Fuel Price: Rs" + request.getFuelPrice() + " per liter, Trip Time: " + request.getTime() + " hours");
                            System.out.println("Estimated Trip Cost: Rs" + tripCost);
                            System.out.println("Average Speed: " + averageSpeed + " km/h");
                            System.out.println();
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
