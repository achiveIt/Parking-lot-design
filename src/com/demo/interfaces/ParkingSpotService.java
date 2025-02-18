package com.demo.interfaces;

public interface ParkingSpotService{

    /**
     * Checks whether the spot is free or not.
     * @return boolean value.
     * */
    boolean isAvailable();

    /**
     * Assign a vehicle the spot, given a vehicle object.
     * @param vehicle: VehicleService Object
     * */
    void parkVehicle(VehicleService vehicle);

    /**
     * Removes a vehicle from the particular parking spot.
     * */
    void removeVehicle();

}
