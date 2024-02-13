package strategies;

import models.Bill;

public interface BillAmountCalculationStrategy {
    double calculateBill(Bill bill);
}
