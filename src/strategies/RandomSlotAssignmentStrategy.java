package strategies;

import enums.ParkingSlotStatus;
import enums.VehicleType;
import exceptions.ParkingLotNotFoundException;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSlot;
import repositories.ParkingLotRepository;

import java.util.Optional;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy {
    private final ParkingLotRepository parkingLotRepository;

    public RandomSlotAssignmentStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Optional<ParkingSlot> getSlot(VehicleType vehicleType, Long gateId) throws ParkingLotNotFoundException {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotByGateId(gateId);

        if (parkingLotOptional.isEmpty()) {
            throw new ParkingLotNotFoundException();
        }

        ParkingLot parkingLot = parkingLotOptional.get();

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
