package com.demo.interfaces;

import com.demo.enums.VehicleType;

import java.util.List;

public interface FloorService {

    /**
     * Parks a vehicle, given vehicle number and its type which can be CAR, BIKE OR TRUCK.
     * @param vehicle: VehicleService Object
     * @return boolean value depicting whether the vehicle is parked or not.
     * */
    List<Integer> parkVehicle(VehicleService vehicle);

    /**
     * Removes vehicle given vehicle number.
     * @param spotNum: .
     * @return boolean value depicting whether the vehicle is removed or not.
     * */
    boolean removeVehicle(Integer spotNum);

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

}