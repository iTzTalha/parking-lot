package services;

import enums.BillStatus;
import enums.ParkingSlotStatus;
import exceptions.GateNotFoundException;
import exceptions.ParkingLotNotFoundException;
import factories.BillAmountCalculationStrategyFactory;
import models.*;
import repositories.BillRepository;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import strategies.CashPaymentStrategy;
import strategies.OnlinePaymentStrategy;
import strategies.PaymentStrategy;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class BillService {
    private final GateRepository gateRepository;
    private final BillRepository billRepository;
    private final ParkingLotRepository parkingLotRepository;

    public BillService(GateRepository gateRepository, BillRepository billRepository, ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.billRepository = billRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Bill issueBill(Long gateId, Ticket ticket) throws GateNotFoundException, ParkingLotNotFoundException {
        Bill bill = new Bill();

        Date exitTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitTime);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date updatedExitDate = calendar.getTime();
        bill.setExitTime(updatedExitDate);

        bill.setTicket(ticket);

        Optional<Gate> gateOptional = gateRepository.getGateById(gateId);
        if (gateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate gate = gateOptional.get();
        bill.setGateGeneratedAt(gate);

        bill.setGeneratedBy(gate.getGateOperator());

        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotByGateId(gateId);
        if (parkingLotOptional.isEmpty()) {
            throw new ParkingLotNotFoundException();
        }
        ParkingLot parkingLot = parkingLotOptional.get();
        double billAmount = BillAmountCalculationStrategyFactory.getBillAmountCalculationStrategyByType(parkingLot.getBillAmountCalculationStrategyType()).calculateBill(bill);
        bill.setAmount(billAmount);

        double cash = billAmount / 2.0;
        double remainingBillAmount = billAmount - cash;
        Payment cashPayment = pay(new CashPaymentStrategy(), cash);
        Payment onlinePayment = pay(new OnlinePaymentStrategy(), remainingBillAmount);
        bill.setPayments(Arrays.asList(cashPayment, onlinePayment));

        bill.setBillStatus(BillStatus.PAID);
        ticket.getParkingSlot().setParkingSlotStatus(ParkingSlotStatus.UNOCCUPIED);

        Bill savedBill = billRepository.saveBill(bill);
        savedBill.setBillNumber("BILL - " + savedBill.getId() + gateId + ticket.getId());

        return savedBill;
    }

    private Payment pay(PaymentStrategy paymentStrategy, double amount) {
        return paymentStrategy.pay(amount);
    }
}
