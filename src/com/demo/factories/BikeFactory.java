package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Bike;

public class BikeFactory implements VehicleFactory{
    private final String vehicleNumber;
    private final VehicleService vehicleService;

    public BikeFactory(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.vehicleService = new Bike(this.vehicleNumber);
    }

    @Override
    public VehicleService createVehicle(){
        return vehicleService;
    }
}
