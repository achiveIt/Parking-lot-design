package com.demo.cli;
import com.demo.interfaces.ParkingLotService;
import com.demo.services.ParkingLotServiceImpl;
import java.util.Scanner;
public class ParkingLotCLI {
    public static void init(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number of floors you would like to have in parking lot: ");
        int  numOfFloors = readPositiveInt(scanner);

        System.out.print("Please enter the number of spots you would like to have in each floor: ");
        int spotsPerFloor = readPositiveInt(scanner);

        ParkingLotService parkingLot = ParkingLotServiceImpl.getInstance(numOfFloors, spotsPerFloor);
        System.out.println("Parking Lot Initialized with " + numOfFloors + " floors and " + spotsPerFloor + " spots per floor.");

        CommandHandler commandHandler = new CommandHandler(parkingLot);
        commandHandler.start();
    }
    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}