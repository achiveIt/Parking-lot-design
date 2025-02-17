package com.demo.factories;

import com.demo.interfaces.VehicleService;

public interface VehicleFactory {
    VehicleService createVehicle(String vehicleNumber);
}
