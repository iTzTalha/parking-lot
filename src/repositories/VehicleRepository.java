package repositories;

import models.Vehicle;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class VehicleRepository {
    private final Map<Long, Vehicle> vehicleMap;
    private static Long previousId = 0L;

    public VehicleRepository() {
        vehicleMap = new TreeMap<>();
    }

    public Optional<Vehicle> getVehicleByNumber(String vehicleNumber) {
        //SELECT * FROM Vehicles WHERE number = :vehicleNumber
        for (Vehicle vehicle : vehicleMap.values()) {
            if (vehicle.getNumber().equals(vehicleNumber)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        previousId++;
        vehicle.setId(previousId);
        vehicle.setCreatedAt(new Date());
        vehicleMap.put(previousId, vehicle);
        return vehicle;
    }
}
