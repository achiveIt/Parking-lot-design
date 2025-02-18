package com.demo.interfaces;

import com.demo.enums.VehicleType;

import java.util.List;

public interface ParkingLotService {

    /**
     *
     * @param vehicleNumber: It is the number plate of the vehicle.
     * @param vehicleType: Tells us whether the particular vehicle is car, bike or truck.
     * @return VehicleService
     */
    VehicleService createVehicle(String vehicleNumber, VehicleType vehicleType);


    /**
     * Checks if the vehicle with the given number already exists or not.
     * @param vehicleNumber: It is the number plate of the vehicle.
     * @return boolean value
     */
    boolean checkUniqueVehicleNumber(String vehicleNumber);

    /**
     * Parks a vehicle, given its number and its type which can be CAR, BIKE OR 
     * TRUCK.(Later we can add more depending on our use case)
     * @param vehicleType: Tells us whether the particular vehicle is car, bike or truck.
     * @param vehicleNumber: It is the number plate of the vehicle.
     * @return List of integer
     * */
    List<Integer> parkVehicle(String vehicleNumber, VehicleType vehicleType);

    /**
     * Removes a vehicle from the parking lot based on its vehicle number
     * @param vehicleNumber:  It is the number plate of the vehicle.
     * @return boolean value
     * */
    boolean removeVehicle(String vehicleNumber);

    /**
     * Gets the number of available spots on a given floor
     * @param floor
     * @return Integer
     * */
    int availableSpots(int floor);

    /**
     * Checks if the parking lot is full or not
     * @return Boolean
     * */
    boolean isFull();

    /**
     * Returns the location of a particular vehicle if it is parked
     * @param vehicleNumber:  It is the number plate of the vehicle.
     * @return String
     * */
    String getVehicleLocation(String vehicleNumber);
}