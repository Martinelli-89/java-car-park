package CarPark;

import parkingSpot.CarSpot;
import parkingSpot.MotorcycleSpot;
import parkingSpot.ParkingSpot;
import parkingSpot.VanSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarPark {

    private List<ParkingSpot> parkingSpaces = new ArrayList<ParkingSpot>();
    public CarPark(int motorcycleSpots, int carSpots, int vansSpot) {
        for (int i=0; i < motorcycleSpots; i++) {
            assert parkingSpaces != null;
            parkingSpaces.add(new MotorcycleSpot());
        }
        for (int y=0; y < carSpots; y++) {
            parkingSpaces.add(new CarSpot());
        }
        for (int z=0; z < vansSpot; z++) {
            parkingSpaces.add(new VanSpot());
        }

    }

    public String isCarParkFull () {

        for(ParkingSpot parkSpot : parkingSpaces) {
            if(parkSpot.getIsAvailable()) {
                return "Our Java Car Park is not full.";
            }
        }
        return "We are sorry but our Java Car Park is currently full.";
    }

    public String isCarParkEmpty () {

        for(ParkingSpot parkSpot : parkingSpaces) {
            if(!parkSpot.getIsAvailable()) {
                return "Our cark is not empty at the moment.";
            }
        }
        return "The car park is currently empty.";
    }

    public int checkCarAvailableSpace () {

        int carSpaces = 0;
        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot.getIsAvailable() && (parkSpot instanceof CarSpot)) {
                carSpaces++;
            }
        }
        return carSpaces;
    }

    public int checkVanAvailableSpace () {

        int vanSpaces = 0;
        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot.getIsAvailable() && (parkSpot instanceof VanSpot)) {
                vanSpaces++;
            }
        }
        return vanSpaces;
    }

    public int checkMotorcycleAvailableSpace () {

        int motorcycleSpaces = 0;
        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot.getIsAvailable() && (parkSpot instanceof MotorcycleSpot)) {
                motorcycleSpaces++;
            }
        }
        return motorcycleSpaces;
    }

    public void checkCarParkAvailability () {

        System.out.println("Our car park has the following spaces available:");
        System.out.println("-Motorcycles : " + this.checkMotorcycleAvailableSpace());
        System.out.println("-Cars : " + this.checkCarAvailableSpace());
        System.out.println("-Vans : " + this.checkVanAvailableSpace());
    }

    public void parkCar () {
        List<ParkingSpot> combinedSpots = new ArrayList<ParkingSpot>();
        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof CarSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your car in the parking spot : " + parkSpot.getParkingSpotNumber());
                return;
            }
        }

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof MotorcycleSpot && parkSpot.getIsAvailable()) {
                combinedSpots.add(parkSpot);
                if(combinedSpots.size() == 3) {
                    String parkingNumber = "";
                    for(ParkingSpot savedParkSpot : combinedSpots) {
                        parkingNumber += ", " + savedParkSpot.getParkingSpotNumber();
                        savedParkSpot.setAvailable();
                    }
                    System.out.println("You can park your car in the motorcycle spots : " + parkingNumber);
                    return;
                }
            }
        }

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof VanSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your car in the van parking spot : " + parkSpot.getParkingSpotNumber());
                return;
            }
        }
        System.out.println("We are afraid, we do not currently have any suitable space for your vehicle.");
    }

    public void parkMotorcycle () {

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof MotorcycleSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your motorcycle in the parking spot : " + parkSpot.getParkingSpotNumber());
                return;
            }
        }

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof CarSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your motorcycle in the car spot : " + parkSpot.getParkingSpotNumber());
                return ;
            }
        }

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof VanSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your motorcycle in the van parking spot : " + parkSpot.getParkingSpotNumber());
                return;
            }
        }
        System.out.println("We are afraid, we do not currently have any suitable space for your vehicle.");
    }

    public void parkVan () {
        List<ParkingSpot> combinedSpots = new ArrayList<ParkingSpot>();
        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof VanSpot && parkSpot.getIsAvailable()) {
                parkSpot.setAvailable();
                System.out.println("You can park your van in the parking spot : " + parkSpot.getParkingSpotNumber());
                return;
            }
        }

        for(ParkingSpot parkSpot : parkingSpaces) {
            if (parkSpot instanceof CarSpot && parkSpot.getIsAvailable()) {
                combinedSpots.add(parkSpot);
                if(combinedSpots.size() == 3) {
                    String parkingNumber = "";
                    for(ParkingSpot savedParkSpot : combinedSpots) {
                        parkingNumber += ", " + savedParkSpot.getParkingSpotNumber();
                        savedParkSpot.setAvailable();
                    }
                    System.out.println("You can park your van in the car spots : " + parkingNumber);
                }
            }
        }
        System.out.println("We are afraid, we do not currently have any suitable space for your vehicle.");
    }

    public void unparkVehicle () {
        Scanner scanner = new Scanner(System.in);
        String parkingNumber = null;
        do {
            System.out.println("Please type the parking you were given and press enter.");
            System.out.println("If your vehicle is occupying multiple space please separate them with a - ");
            String input = scanner.nextLine();
            char[] parkArray = input.toCharArray();
            boolean isEntryValid = true;
            for(char number : parkArray) {
                if(!Character.isDigit(number) && number != '-') {
                    isEntryValid = false;
                }
            }
            if (isEntryValid) {
                parkingNumber = input;
            } else {
                System.out.println("Please try again. Your entry is invalid");
            }
        } while (parkingNumber == null);
        char[] enteredNumber = parkingNumber.toCharArray();
        String tempNumb = "";
        for(int i=0; i < enteredNumber.length; i++) {
            if(enteredNumber[i] != '-' ) {
                tempNumb += enteredNumber[i];
            } else {
                int parkingSpotExtracted = Integer.parseInt(tempNumb);
                tempNumb = "";
                for(ParkingSpot spot : parkingSpaces) {
                    if(spot.getParkingSpotNumber() == parkingSpotExtracted) {
                        spot.setAvailable();
                    }
                }
            }
            if(i == enteredNumber.length-1) {
                int parkingSpotExtracted = Integer.parseInt(tempNumb);
                tempNumb = "";
                for(ParkingSpot spot : parkingSpaces) {
                    if(spot.getParkingSpotNumber() == parkingSpotExtracted) {
                        spot.setAvailable();
                    }
                }
            }
        }
        System.out.println("Your vehicle is ready for you. Thanks for choosing Java Car Park!");
    }
}
