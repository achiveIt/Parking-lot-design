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

    /**
     * returns the vehicle which is parked at that spot
     * @return VehicleService object (null if no vehicle is parked otherwise its object).
     * */
    VehicleService getParkedVehicle();

    /**
     * returns the spot number.
     * @return Integer
     * */
    int getSpotNumber();
}
