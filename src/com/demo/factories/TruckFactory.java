package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Truck;

public class TruckFactory implements VehicleFactory{
    private final String vehicleNumber;
    private final VehicleService vehicleService;

    public TruckFactory(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.vehicleService = new Truck(this.vehicleNumber);
    }

    @Override
    public VehicleService createVehicle(){
        return vehicleService;
    }
}
