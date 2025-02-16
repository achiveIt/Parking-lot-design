package com.demo.services;

import com.demo.interfaces.ParkingSpotService;
import com.demo.interfaces.VehicleService;

public class ParkingSpotServiceImpl implements ParkingSpotService {
    private int spotNumber;
    private VehicleService parkedVehicle;

    public ParkingSpotServiceImpl(int spotNumber){
        this.spotNumber = spotNumber;
        this.parkedVehicle = null;
    }

    @Override
    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    @Override
    public void parkVehicle(VehicleService vehicle) {
        if(isAvailable()){
            this.parkedVehicle = vehicle;
        }else{
            System.out.println("spot number " + spotNumber + " is not available");
        }
    }

    @Override
    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    @Override
    public VehicleService getParkedVehicle() {
        return this.parkedVehicle;
    }

    @Override
    public int getSpotNumber() {
        return this.spotNumber;
    }
}
