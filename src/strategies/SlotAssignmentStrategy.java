package strategies;

import enums.VehicleType;
import exceptions.ParkingLotNotFoundException;
import models.ParkingLot;
import models.ParkingSlot;
import java.util.Optional;

public interface SlotAssignmentStrategy {
    Optional<ParkingSlot> getSlot(VehicleType vehicleType, ParkingLot parkingLot) throws ParkingLotNotFoundException;
}
