package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Car;

public class CarFactory implements VehicleFactory{
    private static CarFactory instance;

    private CarFactory() {}

    public static CarFactory getInstance(){
        if(instance == null){
            instance = new CarFactory();
        }
        return instance;
    }

    @Override
    public VehicleService createVehicle(String vehicleNumber){
        return new Car(vehicleNumber);
    }
}
