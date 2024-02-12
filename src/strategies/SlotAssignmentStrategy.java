package strategies;

import enums.VehicleType;
import exceptions.ParkingLotNotFoundException;
import models.ParkingSlot;

import java.util.Optional;

public interface SlotAssignmentStrategy {
    public Optional<ParkingSlot> getSlot(VehicleType vehicleType, Long gateId) throws ParkingLotNotFoundException;
}
