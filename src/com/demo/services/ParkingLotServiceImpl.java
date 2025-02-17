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
    private Map<String, List<Integer>> vehicleLocation;

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
    public List<Integer> parkVehicle(String vehicleNumber, VehicleType vehicleType) {
        VehicleService vehicle = createVehicle(vehicleNumber, vehicleType);

        List<Integer> parkingInfo = new ArrayList<>();

        for(FloorService floor : floors){
            List<Integer> spotDetails = floor.parkVehicle(vehicle);
            int isPossible = spotDetails.get(0);
            if(isPossible == 1){
                int spotNumber = spotDetails.get(1);
                parkingInfo.add(1);
                parkingInfo.add(floor.getFloorNumber());
                parkingInfo.add(spotNumber);
                vehicleLocation.put(vehicleNumber, parkingInfo);
                return parkingInfo;
            }
        }
        parkingInfo.add(0);
        return parkingInfo;
    }

    @Override
    public boolean removeVehicle(String vehicleNumber) {
        List<Integer> parkingInfo = vehicleLocation.get(vehicleNumber);
        if(parkingInfo == null) return false;

        FloorService floor = floors.get(parkingInfo.get(1) - 1);
        boolean removed = floor.removeVehicle(parkingInfo.get(2));

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
        return noOfSpot == 0;
    }

    @Override
    public String getVehicleLocation(String vehicleNumber) {
        List<Integer> parkingInfo = vehicleLocation.get(vehicleNumber);

        if(parkingInfo == null){
            return "Sorry this vehicle is not parked in our parking lot";
        }

        return "Floor: " + parkingInfo.get(1) + ", Spot: " + parkingInfo.get(2) + 1;
    }
}
