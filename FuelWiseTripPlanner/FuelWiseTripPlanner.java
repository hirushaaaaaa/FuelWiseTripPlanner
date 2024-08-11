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
            // Displaying option menu
            System.out.println("\nSelect any option:");
            System.out.println("1. Calculate Distance Possible");
            System.out.println("2. Calculate Fuel Needed for Distance");
            System.out.println("3. Calculate Fuel Consumption Rate");
            System.out.println("4. Convert Miles to Kilometers");
            System.out.println("5. Convert Kilometers to Miles");
            System.out.println("6. Estimate Cost of Trip");
            System.out.println("7. Calculate Average Speed");
            System.out.println("8. Find Cheapest Fuel Station");
            System.out.println("9. Add Trip Request to Queue");
            System.out.println("10. Display Trip Request Queue");
            System.out.println("11. Calculate Average Fuel Consumption Across Multiple Trips");
            System.out.println("12. Calculate Maximum and Minimum Distances Across Multiple Trips");
            System.out.println("13. Sort and Display Trip Requests by Distance");
            System.out.println("14. Close");

            System.out.println();
            System.out.print("Enter your choice (1-14): ");
            int choice = scanner.nextInt();

            if (choice == 14) {
                System.out.println("Thank you for using FuelWiseTripPlanner");
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
                    System.out.print("Enter trip distance (km): ");
                    double distance = scanner.nextDouble();
                    System.out.print("Enter trip time (hours): ");
                    double time = scanner.nextDouble();
                    trip = new Trip(distance, 0, time);
                    double averageSpeed = trip.calculateAverageSpeed();
                    System.out.println("Average Speed: " + averageSpeed + " km/h");
                    break;

                case 8:
                System.out.print("Enter number of fuel stations: ");
                int numberOfStations;
                
                while (true) {
                    if (scanner.hasNextInt()) {
                        numberOfStations = scanner.nextInt();
                        if (numberOfStations > 0) {
                            break; // Valid input received, exit the loop
                        } else {
                            System.out.print("Please enter a positive integer for the number of stations: ");
                        }
                    } else {
                        System.out.print("Invalid input. Please enter an integer for the number of stations: ");
                        scanner.next(); // Consume the invalid input
                    }
                }
                
                List<FuelStation> stations = new ArrayList<>();
                for (int i = 0; i < numberOfStations; i++) {
                    System.out.print("Enter name of fuel station " + (i + 1) + ": ");
                    String name = scanner.next();
                
                    System.out.print("Enter price of fuel at " + name + ": ");
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Invalid input. Please enter a valid price for fuel at " + name + ": ");
                        scanner.next(); // Consume the invalid input
                    }
                    double price = scanner.nextDouble();
                
                    stations.add(new FuelStation(name, price));
                }
                

                    // Find cheapest station by using PriorityQueue (min-heap)
                    PriorityQueue<FuelStation> pq = new PriorityQueue<>(Comparator.comparingDouble(FuelStation::getPrice));
                    pq.addAll(stations);

                    FuelStation cheapestStation = pq.poll();
                    if (cheapestStation != null) {
                        System.out.println("Cheapest Fuel Station: " + cheapestStation.getName() + " with price Rs" + cheapestStation.getPrice() + " per liter");
                    } else {
                        System.out.println("No stations available.");
                    }
                    break;

                case 9:
                    System.out.print("Enter trip distance (km): ");
                    double queueTripDistance = scanner.nextDouble();
                    System.out.print("Enter fuel price (per liter): ");
                    double queueFuelPrice = scanner.nextDouble();
                    System.out.print("Enter trip time (hours): ");
                    double queueTripTime = scanner.nextDouble();
                    tripQueue.add(new TripRequest(queueTripDistance, queueFuelPrice, queueTripTime));
                    System.out.println("Trip request added to queue.");
                    break;

                case 10:
                    if (tripQueue.isEmpty()) {
                        System.out.println("The trip request queue is empty.");
                    } else {
                        System.out.println("Processing trip request queue...");
                        System.out.println();
                        while (!tripQueue.isEmpty()) {
                            TripRequest request = tripQueue.poll();
                            trip = new Trip(request.getDistance(), request.getFuelPrice(), request.getTime());
                            double tripCostInQueue = trip.estimateCost(vehicle.getFuelConsumptionRate());
                            double averageSpeedInQueue = trip.calculateAverageSpeed();
                            System.out.println("Trip Distance: " + request.getDistance() + " km, Fuel Price: Rs" + request.getFuelPrice() + " per liter, Trip Time: " + request.getTime() + " hours");
                            System.out.println("Estimated Trip Cost: Rs" + tripCostInQueue);
                            System.out.println("Average Speed: " + averageSpeedInQueue + " km/h");
                            System.out.println();
                        }
                    }
                    break;

                case 11:
                    System.out.print("Enter the number of trips: ");
                    int numTrips = scanner.nextInt();
                    double[] fuelConsumptions = new double[numTrips];

                    for (int i = 0; i < numTrips; i++) {
                        System.out.print("Enter distance traveled for trip " + (i + 1) + " (km): ");
                        double distanceTraveledForAvg = scanner.nextDouble();
                        System.out.print("Enter fuel used for trip " + (i + 1) + " (liters): ");
                        double fuelUsedForAvg = scanner.nextDouble();
                        fuelConsumptions[i] = distanceTraveledForAvg / fuelUsedForAvg;
                    }

                    double totalConsumptionRate = 0;
                    for (double rate : fuelConsumptions) {
                        totalConsumptionRate += rate;
                    }

                    double averageConsumptionRate = totalConsumptionRate / numTrips;
                    System.out.println("Average Fuel Consumption Rate: " + averageConsumptionRate + " km/l");
                    break;

                case 12:
                    System.out.print("Enter the number of trips: ");
                    int tripCount = scanner.nextInt();
                    double[] distances = new double[tripCount];

                    for (int i = 0; i < tripCount; i++) {
                        System.out.print("Enter the distance for trip " + (i + 1) + " (km): ");
                        distances[i] = scanner.nextDouble();
                    }

                    double maxDistance = distances[0];
                    double minDistance = distances[0];

                    for (int i = 1; i < distances.length; i++) {
                        if (distances[i] > maxDistance) {
                            maxDistance = distances[i];
                        }
                        if (distances[i] < minDistance) {
                            minDistance = distances[i];
                        }
                    }

                    System.out.println("Maximum Distance Traveled: " + maxDistance + " km");
                    System.out.println("Minimum Distance Traveled: " + minDistance + " km");
                    break;

                case 13:
                    List<TripRequest> tripRequestsList = new ArrayList<>(tripQueue);

                    if (tripRequestsList.isEmpty()) {
                        System.out.println("No trips have been entered.");
                    } else {
                        // Implementing bubble sort
                        int n = tripRequestsList.size();
                        for (int i = 0; i < n - 1; i++) {
                            for (int j = 0; j < n - i - 1; j++) {
                                if (tripRequestsList.get(j).getDistance() > tripRequestsList.get(j + 1).getDistance()) {
                                    // Swap the elements if they are in the wrong order
                                    TripRequest temp = tripRequestsList.get(j);
                                    tripRequestsList.set(j, tripRequestsList.get(j + 1));
                                    tripRequestsList.set(j + 1, temp);
                                }
                            }
                        }
                
                        System.out.println("Sorted Trip Requests by Distance (Bubble Sort):");
                        for (TripRequest sortedRequest : tripRequestsList) {
                            System.out.println("Trip Distance: " + sortedRequest.getDistance() + " km, Fuel Price: Rs" + sortedRequest.getFuelPrice() + " per liter, Trip Time: " + sortedRequest.getTime() + " hours");
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

// Supporting classes like Vehicle, Trip, FuelStation, and TripRequest would be defined here or in separate files.
