package strategies;

import models.Bill;

public class TimeBasedBillAmountCalculationStrategy implements BillAmountCalculationStrategy {
    private final double MINUTE_CHARGE = 0.5d;

    @Override
    public double calculateBill(Bill bill) {
        long timeDifference = bill.getExitTime().getTime() - bill.getTicket().getEntryTime().getTime();
        long differenceInHours = timeDifference / (60 * 60 * 1000);
        return differenceInHours * (MINUTE_CHARGE * 60);
    }
}
