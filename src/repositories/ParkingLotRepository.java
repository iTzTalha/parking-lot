package repositories;

import enums.BillAmountCalculationStrategyType;
import enums.ParkingLotStatus;
import enums.SlotAssignmentStrategyType;
import enums.VehicleType;
import exceptions.GateNotFoundException;
import exceptions.LocationNotFoundException;
import exceptions.ParkingFloorNotFoundException;
import models.Gate;
import models.Location;
import models.ParkingFloor;
import models.ParkingLot;

import java.util.*;

public class ParkingLotRepository {
    private final Map<Long, ParkingLot> parkingLotMap;
    private static Long previousId = 0L;

    public ParkingLotRepository() {
        parkingLotMap = new TreeMap<>();
    }

    public void populateDummyParkingLots(LocationRepository locationRepository, GateRepository gateRepository, ParkingFloorRepository parkingFloorRepository) throws LocationNotFoundException, GateNotFoundException, ParkingFloorNotFoundException {
        ParkingLot parkingLot = new ParkingLot();
        Optional<Location> parkingLotLocationOptional = locationRepository.getLocationById(3L);
        if (parkingLotLocationOptional.isEmpty()) {
            throw new LocationNotFoundException();
        }
        Location parkingLotLocation = parkingLotLocationOptional.get();
        parkingLot.setLocation(parkingLotLocation);
        Optional<ParkingFloor> parkingFloorOptional = parkingFloorRepository.getParkingFloorsById(1L);
        if (parkingFloorOptional.isEmpty()) {
            throw new ParkingFloorNotFoundException();
        }
        ParkingFloor parkingFloor = parkingFloorOptional.get();
        parkingLot.setParkingFloors(List.of(parkingFloor));

        Optional<Gate> entryGateOptional = gateRepository.getGateById(1L);
        if (entryGateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate entryGate = entryGateOptional.get();
        Optional<Gate> exitGateOptional = gateRepository.getGateById(2L);
        if (exitGateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate exitGate = exitGateOptional.get();
        parkingLot.setGates(Arrays.asList(entryGate, exitGate));
        parkingLot.setSupportedVehicleTypes(Arrays.asList(VehicleType.BIKE, VehicleType.CAR, VehicleType.BUS, VehicleType.TRUCK));
        parkingLot.setParkingLotStatus(ParkingLotStatus.OPEN);
        parkingLot.setSlotAssignmentStrategyType(SlotAssignmentStrategyType.RANDOM);
        parkingLot.setBillAmountCalculationStrategyType(BillAmountCalculationStrategyType.TIME_BASED);
        saveParkingLot(parkingLot);
    }

    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        previousId++;
        parkingLot.setId(previousId);
        parkingLot.setCreatedAt(new Date());
        parkingLotMap.put(previousId, parkingLot);
        return parkingLot;
    }

    public Optional<ParkingLot> getParkingLotByGateId(Long gateId) {
        //SELECT <ParkingLot Fields> FROM Gates AS g
        //JOIN ParkingLots AS pl
        //ON g.parkingLot_id = pl.id

        for (ParkingLot parkingLot : parkingLotMap.values()) {
            for (Gate gate : parkingLot.getGates()) {
                if (gate.getId() == gateId) {
                    return Optional.of(parkingLot);
                }
            }
        }

        return Optional.empty();
    }
}
