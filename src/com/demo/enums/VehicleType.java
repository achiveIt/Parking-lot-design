package com.demo.enums;

import java.util.ArrayList;
import java.util.List;

public enum VehicleType {
    BIKE,
    CAR,
    TRUCK;

    public static final List<String> VEHICLE_TYPES = List.of(BIKE.toString(), CAR.toString(), TRUCK.toString());
}
