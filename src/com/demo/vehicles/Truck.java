package com.demo.vehicles;

import com.demo.enums.VehicleType;
import com.demo.interfaces.VehicleService;

public class Truck implements VehicleService {
    private final String numberPlate;
    private final VehicleType vehicleType;

    public Truck(String numberPlate) {
        this.numberPlate = numberPlate;
        this.vehicleType =  VehicleType.TRUCK;
    }

    @Override
    public String getVehicleNumber() {
        return numberPlate;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }
}
