package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Truck;

public class TruckFactory implements VehicleFactory{
    private static TruckFactory instance;

    private TruckFactory(){}

    public static TruckFactory getInstance() {
        if(instance == null){
            instance = new TruckFactory();
        }
        return instance;
    }

    @Override
    public VehicleService createVehicle(String vehicleNumber){
        return new Truck(vehicleNumber);
    }
}
