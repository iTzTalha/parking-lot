import controllers.TicketController;
import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
import enums.VehicleType;
import repositories.*;
import services.TicketService;

public class Client {
    public static void main(String[] args) {
        try {
            LocationRepository locationRepository = new LocationRepository();

            OperatorRepository operatorRepository = new OperatorRepository(locationRepository);

            GateRepository gateRepository = new GateRepository(operatorRepository);

            ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
            parkingFloorRepository.populateDummyParkingFloors();

            ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
            parkingLotRepository.populateDummyParkingLots(locationRepository, gateRepository, parkingFloorRepository);

            VehicleRepository vehicleRepository = new VehicleRepository();

            TicketRepository ticketRepository = new TicketRepository();

            TicketService ticketService = new TicketService(gateRepository, vehicleRepository, parkingLotRepository, ticketRepository);

            TicketController ticketController = new TicketController(ticketService);

            IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
            requestDTO.setGateId(1L);
            requestDTO.setVehicleNumber("LY 1056 XL");
            requestDTO.setOwnerName("Tsuki");
            requestDTO.setVehicleType(VehicleType.CAR);
            IssueTicketResponseDTO responseDTO = ticketController.issueTicket(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
