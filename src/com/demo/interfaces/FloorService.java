package com.demo.interfaces;

import com.demo.enums.VehicleType;

public interface FloorService {

    /**
     * Parks a vehicle, given vehicle number and its type which can be CAR, BIKE OR TRUCK.
     * @param vehicle: VehicleService Object
     * @return boolean value depicting whether the vehicle is parked or not.
     * */
    boolean parkVehicle(VehicleService vehicle);

    /**
     * Removes vehicle given vehicle number.
     * @param vehicleNumber: vehicle number based on which we will identify the vehicle to be removed.
     * @return boolean value depicting whether the vehicle is removed or not.
     * */
    boolean removeVehicle(String vehicleNumber);

    /**
     * Get List of available spots in respect to a particular floor
     * @return Integer value depicting number of free slots.
     * */
    int availableSpots();

    /**
     * Returns the floor number.
     * @return Integer value.
     * */
    int getFloorNumber();

    /**
     * Returns vehicle spot on that particular floor.
     * @return Integer value.
     * */
    int getVehicleSpot(String vehicleNumber);
}