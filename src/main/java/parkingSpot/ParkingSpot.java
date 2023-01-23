package parkingSpot;

public class ParkingSpot {

    static int parkingSpotCounter = 1;
    private int parkingSpotNumber;
    private boolean isAvailable = true;
    private String parkType;

    public ParkingSpot () {
        parkingSpotNumber = parkingSpotCounter;
        this.parkingSpotCounter++;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable() {
        isAvailable = !this.getIsAvailable();
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }
}
