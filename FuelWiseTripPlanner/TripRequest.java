public class TripRequest {
    private double distance;
    private double fuelPrice;
    private double time;

    public TripRequest(double distance, double fuelPrice, double time) {
        this.distance = distance;
        this.fuelPrice = fuelPrice;
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public double getFuelPrice() {
        return fuelPrice;
    }

    public double getTime() {
        return time;
    }
}
