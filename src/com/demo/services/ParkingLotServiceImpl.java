package com.demo.services;

import com.demo.Constants;
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
    private static ParkingLotServiceImpl instance;
    private List<FloorService> floors;
    private Map<String, List<Integer>> vehicleLocation;

    private ParkingLotServiceImpl(int numOfFloors, int numOfSpotsPerFloor) {
        this.floors = new ArrayList<>();
        this.vehicleLocation = new HashMap<>();

        for (int i = 0; i < numOfFloors; i++) {
            floors.add(new FloorServiceImpl(i + 1, numOfSpotsPerFloor));
        }
    }

    public static synchronized ParkingLotServiceImpl getInstance(int numOfFloors, int numOfSpotsPerFloor) {
        if (instance == null) {
            instance = new ParkingLotServiceImpl(numOfFloors, numOfSpotsPerFloor);
        }
        return instance;
    }

    @Override
    public VehicleService createVehicle(String vehicleNumber, VehicleType vehicleType){
        VehicleFactory vehicleFactory;

        switch(vehicleType){
            case VehicleType.CAR:
                vehicleFactory = CarFactory.getInstance();
                break;
            case VehicleType.BIKE:
                vehicleFactory = BikeFactory.getInstance();
                break;
            case VehicleType.TRUCK:
                vehicleFactory = TruckFactory.getInstance();
                break;
            default:
                return null;
        }

        return vehicleFactory.createVehicle(vehicleNumber);
    }


    @Override
    public boolean checkUniqueVehicleNumber(String vehicleNumber) {
        return vehicleLocation.get(vehicleNumber) == null;
    }

    @Override
    public List<Integer> parkVehicle(String vehicleNumber, VehicleType vehicleType) {
        VehicleService vehicle = createVehicle(vehicleNumber, vehicleType);

        List<Integer> parkingInfo = new ArrayList<>();

        for(FloorService floor : floors){
            List<Integer> spotDetails = floor.parkVehicle(vehicle);
            int isPossible = spotDetails.get(0);
            if(isPossible == 1){
                if(vehicleType == VehicleType.TRUCK){
                    parkingInfo.add(Constants.TRUCK_SPOTS);
                }else{
                    parkingInfo.add(Constants.BIKE_SPOTS);
                }
                int spotNumber = spotDetails.get(1);
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
        boolean removed = false;

        if(parkingInfo.get(0) == Constants.TRUCK_SPOTS){
            FloorService floor = floors.get(parkingInfo.get(1) - 1);
            removed = floor.removeVehicle(parkingInfo.get(2));
            removed = floor.removeVehicle((parkingInfo.get(2) + 1));
        }else{
            FloorService floor = floors.get(parkingInfo.get(1) - 1);
            removed = floor.removeVehicle(parkingInfo.get(2));
        }

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

        return "Floor: " + parkingInfo.get(1) + ", Spot: " + (parkingInfo.get(2) + 1);
    }
}
