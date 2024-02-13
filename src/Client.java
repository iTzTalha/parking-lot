import controllers.BillController;
import controllers.TicketController;
import dtos.IssueBillRequestDTO;
import dtos.IssueBillResponseDTO;
import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
import enums.VehicleType;
import models.Bill;
import models.Payment;
import repositories.*;
import services.BillService;
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

            IssueTicketRequestDTO issueTicketRequestDTO = new IssueTicketRequestDTO();
            issueTicketRequestDTO.setGateId(1L);
            issueTicketRequestDTO.setVehicleNumber("LY 1056 XL");
            issueTicketRequestDTO.setOwnerName("Tsuki");
            issueTicketRequestDTO.setVehicleType(VehicleType.CAR);
            IssueTicketResponseDTO issueTicketResponseDTO = ticketController.issueTicket(issueTicketRequestDTO);

            BillRepository billRepository = new BillRepository();

            BillService billService = new BillService(gateRepository, billRepository, parkingLotRepository);

            BillController billController = new BillController(billService);

            IssueBillRequestDTO issueBillRequestDTO = new IssueBillRequestDTO();
            issueBillRequestDTO.setGateId(2L);
            issueBillRequestDTO.setTicket(issueTicketResponseDTO.getTicket());

            IssueBillResponseDTO issueBillResponseDTO = billController.issueBill(issueBillRequestDTO);

            Bill bill = issueBillResponseDTO.getBill();

            for (Payment payment : bill.getPayments()) {
                System.out.println("PAID " + payment.getAmount() + " by " + payment.getPaymentMode());
            }

            System.out.println("PAID TOTAL - " + bill.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
