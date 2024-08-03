public class Trip {
    private double distance; 
    private double fuelPrice; 
    private double time; 

    public Trip(double d, double fp, double t) {
        distance = d;
        fuelPrice = fp;
        time = t;
    }

    public double estimateCost(double fuelConsumptionRate) {
        double fuelNeeded = distance / fuelConsumptionRate;
        return fuelNeeded * fuelPrice;
    }

    public double calculateAverageSpeed() {
        return distance / time;
    }
}