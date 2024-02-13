package strategies;

import enums.ParkingSlotStatus;
import enums.VehicleType;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSlot;
import java.util.Optional;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy {
    @Override
    public Optional<ParkingSlot> getSlot(VehicleType vehicleType, ParkingLot parkingLot) {
        for (ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
            for (ParkingSlot parkingSlot : parkingFloor.getParkingSlots()) {
                if (parkingSlot.getParkingSlotStatus().equals(ParkingSlotStatus.UNOCCUPIED)
                        && parkingSlot.getAllowedVehicleTypes().contains(vehicleType)) {
                    return Optional.of(parkingSlot);
                }
            }
        }

        return Optional.empty();
    }
}
