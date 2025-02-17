package com.demo.services;

import com.demo.enums.VehicleType;
import com.demo.factories.VehicleFactory;
import com.demo.factories.CarFactory;
import com.demo.factories.BikeFactory;
import com.demo.factories.TruckFactory;
import com.demo.interfaces.FloorService;
import com.demo.interfaces.ParkingLotService;
import com.demo.interfaces.VehicleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotServiceImpl implements ParkingLotService {
    private List<FloorService> floors;
    private Map<String, Integer> vehicleLocation;

    public ParkingLotServiceImpl(int numOfFloors, int numOfSpotsPerFloor) {
        this.floors = new ArrayList<>();
        this.vehicleLocation = new HashMap<>();

        for(int i = 0; i < numOfFloors; i++){
            floors.add(new FloorServiceImpl(i + 1, numOfSpotsPerFloor));
        }
    }

    public VehicleService createVehicle(String vehicleNumber, VehicleType vehicleType) {
        VehicleFactory vehicleFactory;

        switch (vehicleType) {
            case CAR:
                vehicleFactory = new CarFactory(vehicleNumber);
                break;
            case BIKE:
                vehicleFactory = new BikeFactory(vehicleNumber);
                break;
            case TRUCK:
                vehicleFactory = new TruckFactory(vehicleNumber);
                break;
            default:
                return null;
        }

        return vehicleFactory.createVehicle();
    }


    @Override
    public boolean parkVehicle(String vehicleNumber, VehicleType vehicleType) {
        VehicleService vehicle = createVehicle(vehicleNumber, vehicleType);

        if(vehicle == null) return false;

        for(FloorService floor : floors){
            if(floor.parkVehicle(vehicle)){
                vehicleLocation.put(vehicleNumber, floor.getFloorNumber());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeVehicle(String vehicleNumber) {
        Integer floorNumber = vehicleLocation.get(vehicleNumber);
        if(floorNumber == null) return false;

        FloorService floor = floors.get(floorNumber - 1);
        boolean removed = floor.removeVehicle(vehicleNumber);

        if(removed) vehicleLocation.remove(vehicleNumber);
        return removed;
    }

    @Override
    public int availableSpots(int floor) {
        if(floor< 1 || floor>floors.size()){
            return -1;
        }

        return floors.get(floor - 1).availableSpots();
    }

    @Override
    public boolean isFull() {
        int noOfSpot = 0;
        for(FloorService floor: floors){
            noOfSpot += floor.availableSpots();
        }
        return noOfSpot == 0 ?  true : false;
    }

    @Override
    public String getVehicleLocation(String vehicleNumber) {
        Integer floorNum = vehicleLocation.get(vehicleNumber);

        if(floorNum == null){
            return "Sorry this vehicle is not parked in our parking lot";
        }

        FloorService floor = floors.get(floorNum - 1);
        Integer spotNum = floor.getVehicleSpot(vehicleNumber);

        return "Floor: " + floorNum + ", Spot: " + spotNum;
    }
}
