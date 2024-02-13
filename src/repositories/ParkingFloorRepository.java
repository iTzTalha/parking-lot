package repositories;

import enums.ParkingFloorStatus;
import exceptions.ParkingFloorNotFoundException;
import models.ParkingFloor;

import java.util.*;

public class ParkingFloorRepository {
    private final Map<Long, ParkingFloor> parkingFloorMap;
    private static Long previousId = 0L;

    public ParkingFloorRepository() {
        parkingFloorMap = new TreeMap<>();
    }

    public void populateDummyParkingFloors() throws ParkingFloorNotFoundException {
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorNumber("XYZ");
        parkingFloor.setParkingFloorStatus(ParkingFloorStatus.OPEN);
        saveParkingFloor(parkingFloor);
        ParkingSlotRepository parkingSlotRepository = new ParkingSlotRepository();
        parkingSlotRepository.populateDummyParkingSlots(this);
    }

    public ParkingFloor saveParkingFloor(ParkingFloor parkingFloor) {
        previousId++;
        parkingFloor.setId(previousId);
        parkingFloor.setCreatedAt(new Date());
        parkingFloorMap.put(previousId, parkingFloor);
        return parkingFloor;
    }

    public Optional<ParkingFloor> getParkingFloorsById(Long parkingFloorId) {
        if (parkingFloorMap.containsKey(parkingFloorId))
            return Optional.ofNullable(parkingFloorMap.get(parkingFloorId));
        return Optional.empty();
    }
}
