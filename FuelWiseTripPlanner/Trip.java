public class Trip {
    private double distance; 
    private double fuelPrice; 
    private double time; 

    public Trip(double distance, double fuelPrice, double time) {
        this.distance = distance;
        this.fuelPrice = fuelPrice;
        this.time = time;
    }

    public double estimateCost(double fuelConsumptionRate) {
        return (distance / fuelConsumptionRate) * fuelPrice;
    }

    public double calculateAverageSpeed() {
        return distance / time;
    }
}
