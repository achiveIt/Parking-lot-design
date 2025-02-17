package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Car;

public class CarFactory implements VehicleFactory{
    private final String vehicleNumber;
    private final VehicleService vehicleService;

    public CarFactory(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.vehicleService = new Car(this.vehicleNumber);
    }

    @Override
    public VehicleService createVehicle(){
        return vehicleService;
    }
}
