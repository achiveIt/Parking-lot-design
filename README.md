# Parking lot design 
### Developed By Shivdutt Pachori


## Project Overview:
This project is about Parking Lot Design which has been implemented taken into consideration different design patterns like singleton, factory etc. The language used is Java and the IDE used is Intellij Idea. The project supports multiple floors, each having a same number of spots,  currently the parking of three vehicles Car, Bike and Truck is supported but it is extendible to adding more vehicles as well. Along with it a Command-line interface is also provided for user interaction.

## Tech Stack
- Programming Language: **Java**
- IDE: **IntelliJ IDEA**

## Features
- User can check whether the parking lot is full or not.
- Find number of available spots per floor.
- User can park a vehicle given its vehicle number.
- Find the location (floor and spot) of a vehicle given its vehicle number.
- Removing a vehicle from its parked place given vehicle number.
- **CLI**-based interaction

## Getting Started

###  Clone the Repository
Clone the project from GitHub:
``` bash
git clone https://github.com/achiveIt/Parking-lot-design
cd Parking-lot-design
```

## Detailed Explanation
    
### Parking Lot Service


The ParkingLotService is a core part of the parking lot system design which is responsible for managing parking operations such as parking vehicle, removing parked vehicle, find available spots per floor, location of a parked vehicle and to let the users know is the parking lot is full or not.

The implementation of parkingLotService is made as a **_singleton_** class to ensure all the vehicles are parked on the same parkingLot, this approach prevents the creation of multiple ParkingLot instances each time a vehicle arrives, maintaining consistency across all parking operations.

The class implementing it contains a list of Floors as a parkinglot can have multiple floors, map is used to store vehicle number corresponding to which we are storing the location of parked vehicle for **_fast vehicle lookup_**.

ParkingLotService methods interact directly with users, whenever a new vehicles comes in for parking it first comes to parkingLot and from there parkingLot traverses the list of floors and then the floor further down checks it list of spots to find a available spot.

When a user comes to drive away their vehicle, we directly fetch its location from the map we were maintaining and passes the floor and spot number fetched to floorServiceImpl and ParkingSpotService Impl to free up the space.

### Floor Service

FloorService is an **_interface_** that provides functionality to manage multiple parking spots on a floor. It contains a list of ParkingSpots, as each floor can have multiple parking spots associated with it. Additionally, each floor is identified by a unique floor number.

### Parking spot service

ParkingSpotService is an **_interface_** that provides various functionalities for managing parking spots. It includes methods for parking a vehicle at a spot if it is available, unparking the vehicle when requested by the user, and checking whether a particular spot is free or occupied.

### Vehicle Service

VehicleService is an **_interface_** to which a new vehicle extends to, we made this to ensure flexibility, extensibility and consistency across the vehicle type classes.

Along with it, we have also created an enum class named **_VehicleType_** containing **_BIKE, CAR,_** and **_TRUCK_**, making it easily extensible for future vehicle types. In addition to it, we have also made a **_constant_** file which stores the number of consecutive slots required by a particular vehicle the sole purpose of doing this was to eliminate the risk of errors due to incorrect string values, ensuring consistency throughout the codebase. Additionally, it enhances **_maintainability_**, as new vehicle types can be added in one place without affecting the existing logic.



### Vehicle Factory

VehicleFactory is an **_interface_** implemented by all vehicle types to standardize object creation. By using the createVehicle() function in VehicleFactory, we can create vehicle objects without directly instantiating them, making the code more **_modular_** and **_extendable_** following the **_factory design pattern_**. To optimize memory usage, the factory classes implementing this interface are designed as **_singleton classes_**, ensuring that only one instance of each factory is created. This approach prevents unnecessary memory consumption while maintaining consistency in object creation.

We can understand it by an analogy, whenever we need to create a product each time we don't create a new factory (which produces the product) instead we just ask the same factory to create a new product for us, so following the same analogy, I made the class singleton.

By defining all these services as an **interface**, the code becomes more **_modular_** and **_maintainable_**, allowing for different implementations of parking logic while ensuring a consistent set of operations for managing parking spots.