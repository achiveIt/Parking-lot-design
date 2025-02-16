package com.demo.interfaces;

import com.demo.enums.VehicleType;

public interface ParkingLotService {

    /**
     * Parks a vehicle, given its number and its type which can be CAR, BIKE OR 
     * TRUCK.(Later we can add more depending on our use case)
     * @param vehicleType: Tells us whether the particular vehicle is car, bike or truck.
     * @param vehicleNumber: It is the number plate of the vehicle.
     * @return boolean value
     * */
    boolean parkVehicle(String vehicleNumber, VehicleType vehicleType);

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