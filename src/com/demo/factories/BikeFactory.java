package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Bike;

public class BikeFactory implements VehicleFactory{
    private static BikeFactory instance;

    private BikeFactory(){}

    public static BikeFactory getInstance(){
        if(instance == null){
            instance = new BikeFactory();
        }
        return instance;
    }

    @Override
    public VehicleService createVehicle(String vehicleNumber){
        return new Bike(vehicleNumber);
    }
}
