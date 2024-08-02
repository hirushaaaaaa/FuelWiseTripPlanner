public class Vehicle {
    private double fuelConsumptionRate; 
    private double fuelTankCapacity;    

    public Vehicle(double fuelConsumptionRate, double fuelTankCapacity) {
        this.fuelConsumptionRate = fuelConsumptionRate;
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public double getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public double calculateDistancePossible(double fuelAmount) {
        return fuelConsumptionRate * fuelAmount;
    }

    public double calculateFuelNeeded(double distance) {
        return distance / fuelConsumptionRate;
    }
}
