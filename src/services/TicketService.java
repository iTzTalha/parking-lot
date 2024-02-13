package services;

import enums.ParkingSlotStatus;
import enums.VehicleType;
import exceptions.*;
import factories.SlotAssignmentStrategyFactory;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import java.util.Date;
import java.util.Optional;

public class TicketService {
    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateId, String vehicleNumber, VehicleType vehicleType, String ownerName) throws GateNotFoundException, ParkingLotNotFoundException, ParkingSlotNotFoundException, OperatorNotFoundException, ParkingFloorNotFoundException, LocationNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate> gateOptional = gateRepository.getGateById(gateId);
        if (gateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate gate = gateOptional.get();
        ticket.setGateGeneratedAt(gate);

        ticket.setGeneratedBy(gate.getGateOperator());

        Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByNumber(vehicleNumber);
        Vehicle savedVehicle;
        if (vehicleOptional.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(ownerName);
            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        } else {
            savedVehicle = vehicleOptional.get();
        }
        ticket.setVehicle(savedVehicle);

        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotByGateId(gateId);
        if (parkingLotOptional.isEmpty()) {
            throw new ParkingLotNotFoundException();
        }
        ParkingLot parkingLot = parkingLotOptional.get();
        Optional<ParkingSlot> parkingSlotOptional = SlotAssignmentStrategyFactory.getSlotAssignmentStrategyByType(parkingLot.getSlotAssignmentStrategyType()).getSlot(vehicleType, parkingLot);
        if (parkingSlotOptional.isEmpty()) {
            throw new ParkingSlotNotFoundException();
        }
        ParkingSlot parkingSlot = parkingSlotOptional.get();
        parkingSlot.setParkingSlotStatus(ParkingSlotStatus.OCCUPIED);
        ticket.setParkingSlot(parkingSlot);

        Ticket savedTicket = ticketRepository.saveTicket(ticket); //Comes with the ticked id from the DB;
        savedTicket.setTicketNumber("TICKET - " + savedTicket.getId() + gateId + vehicleNumber);

        return savedTicket;
    }
}
