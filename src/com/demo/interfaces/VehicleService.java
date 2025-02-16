package com.demo.interfaces;

import com.demo.enums.VehicleType;

public interface VehicleService {

    /**
     * returns the vehicle number
     * @return String.
     * */
    String getVehicleNumber();

    /**
     * returns the type of vehicle.
     * @return Enum VehicleType.
     * */
    VehicleType getType();
}
