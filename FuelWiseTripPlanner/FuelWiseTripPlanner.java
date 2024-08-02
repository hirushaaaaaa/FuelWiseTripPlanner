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
     }
}
