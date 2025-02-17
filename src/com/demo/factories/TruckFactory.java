package com.demo.factories;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Truck;

import static com.demo.enums.VehicleType.TRUCK;

public class TruckFactory implements vehicleFactory{

    private String vehicleNumber;
    private VehicleService vehicleService;

    public TruckFactory(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.vehicleService = new Truck(this.vehicleNumber);
    }
    @Override
    public VehicleService createVehicle() {
        return vehicleService;
    }
}
