package com.demo.services;

import com.demo.Constants;
import com.demo.enums.VehicleType;
import com.demo.interfaces.FloorService;
import com.demo.interfaces.ParkingSpotService;
import com.demo.interfaces.VehicleService;

import java.util.ArrayList;
import java.util.List;

public class FloorServiceImpl implements FloorService {
    private int floorNumber;
    private List<ParkingSpotService> spots;

    public FloorServiceImpl(int floorNumber, int numberOfSpots){
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();

        for(int i = 0; i < numberOfSpots;  i++){
            spots.add(new ParkingSpotServiceImpl(i + 1));
        }
    }

    @Override
    public List<Integer> parkVehicle(VehicleService vehicle) {
        VehicleType vehicleType = vehicle.getType();
        List<Integer> list = new ArrayList<>();
        int requiredSpots = 0;
        if(vehicleType == VehicleType.TRUCK){
            requiredSpots = Constants.TRUCK_SPOTS;
        }
        else if(vehicleType == VehicleType.CAR){
            requiredSpots = Constants.CAR_SPOTS;
        }
        else if(vehicleType == VehicleType.BIKE){
            requiredSpots = Constants.BIKE_SPOTS;
        }

        for(int i = 0; i <= spots.size() - requiredSpots; i++){
            boolean canPark = true;

            for(int j = 0; j < requiredSpots; j++){
                if(!spots.get(i+j).isAvailable()){
                    canPark = false;
                    break;
                }
            }

            if(canPark){
                for(int j = 0; j < requiredSpots; j++){
                    spots.get(i+j).parkVehicle(vehicle);
                }
                list.add(1);
                list.add(i);
                return list;
            }

        }

        list.add(0);
        return list;
    }

    @Override
    public boolean removeVehicle(Integer spotNum) {
        spots.get(spotNum).removeVehicle();
        return true;
    }

    @Override
    public int availableSpots() {
        int noOfAvailableSpots = 0;
        for(ParkingSpotService spot: spots){
            if(spot.isAvailable()){
                noOfAvailableSpots++;
            }
        }
        return noOfAvailableSpots;
    }

    @Override
    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public int getVehicleSpot(String vehicleNumber) {
        for(ParkingSpotService spot: spots){
            if(!spot.isAvailable() && spot.getParkedVehicle().getVehicleNumber().equals(vehicleNumber)){
                return spot.getSpotNumber();
            }
        }
        return -1;
    }
}
