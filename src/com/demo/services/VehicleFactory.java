package com.demo.services;

import com.demo.interfaces.VehicleService;
import com.demo.vehicles.Car;
import com.demo.vehicles.Bike;
import com.demo.vehicles.Truck;
import com.demo.enums.VehicleType;

public class VehicleFactory {
    public static VehicleService createVehicle(String vehicleRegNo, VehicleType type) {
        switch (type) {
            case CAR:
                return new Car(vehicleRegNo);
            case BIKE:
                return new Bike(vehicleRegNo);
            case TRUCK:
                return new Truck(vehicleRegNo);
            default:
                return null;
        }
    }


}
