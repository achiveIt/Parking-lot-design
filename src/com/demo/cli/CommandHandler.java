package com.demo.cli;

import com.demo.interfaces.ParkingLotService;
import com.demo.enums.VehicleType;

import java.util.List;

import static com.demo.enums.VehicleType.VEHICLE_TYPES;

public class CommandHandler {
    private ParkingLotService parkingLot;
    private InputReader inputReader;

    public CommandHandler(ParkingLotService parkingLot){
        this.parkingLot = parkingLot;
        this.inputReader = new InputReader();
    }

    public void start(){
        while(true){
            printMenu();
            int choice = inputReader.readInt("Enter your choice: ");

            switch(choice){
                case 1 -> checkIfFull();
                case 2 -> checkAvailableSpots();
                case 3 -> parkVehicle();
                case 4 -> findVehicle();
                case 5 -> removeVehicle();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void printMenu(){
        System.out.println("\nChoose an option:");
        System.out.println("1. Check if Parking Lot is Full");
        System.out.println("2. Check Available Spots on a particular floor");
        System.out.println("3. Park Vehicle");
        System.out.println("4. Get Vehicle Location");
        System.out.println("5. Remove vehicle");
        System.out.println("6. Exit");
    }

    private void checkIfFull(){
        if(parkingLot.isFull()){
            System.out.println("Sorry the parking lot is full");
        }else{
            System.out.println("Parking spaces are available");
        }
    }

    private void checkAvailableSpots(){
        int floor = inputReader.readInt("Please enter floor number: ");
        int numOfSpotsAvailable = parkingLot.availableSpots(floor);

        if(numOfSpotsAvailable == -1){
            System.out.println("The floor number you have entered is invalid");
        }else{
            System.out.println("Number of slots available on floor " + floor + ": " + numOfSpotsAvailable);
        }
    }

    private void parkVehicle(){
        String vehicleNumber = inputReader.readString("Please enter your vehicle number: ");
        String vehicleType = inputReader.readString("Please enter your vehicle type (Truck, Bike or Car): ").toUpperCase();

        if (!VEHICLE_TYPES.contains(vehicleType)) {
            System.out.println("Invalid vehicle type Please enter one of: " + VEHICLE_TYPES);
            start();
        }

        VehicleType type = VehicleType.valueOf(vehicleType);
        List<Integer> parkingInfo = parkingLot.parkVehicle(vehicleNumber, type);

        if(parkingInfo.get(0) == 1){
            System.out.println("Vehicle number " + vehicleNumber + " parked successfully at Location: Floor: " + parkingInfo.get(1) + " Spot: " + (parkingInfo.get(2) + 1));
        }else{
            System.out.println("Sorry no space left to park the vehicle");
        }

    }

    private void findVehicle(){
        String vehicleNumber = inputReader.readString("Enter vehicle number: ");
        String locationOfVehicle = parkingLot.getVehicleLocation(vehicleNumber);

        System.out.println("Vehicle " + vehicleNumber + " is parked at: " + locationOfVehicle);
    }

    private void removeVehicle(){
        String vehicleNumber = inputReader.readString("Please enter vehicle number to remove: ");
        boolean parkingStatus = parkingLot.removeVehicle(vehicleNumber);

        if(parkingStatus){
            System.out.println("Vehicle removed successfully");
        }else{
            System.out.println("The vehicle with " + vehicleNumber + " is not parked in the parking lot");
        }
    }

}
