public class Trip{
    private double distance;
    private double fuelPrice;
    private double time;

    public Trip (double distance, double fuelPrice, double time){
        this.distance = distance;
        this.fuelPrice = fuelPrice;
        this.time = time;
    }

    public double estimatedCost (double fuelConsumptionRate){
        double fuelNeeded = distance / fuelConsumptionRate;
        return fuelNeeded * fuelPrice;
    }

    public double calculateAverageSpeed(){
        return distance / time;
    }
    
}