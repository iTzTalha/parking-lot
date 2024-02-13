package strategies;

import enums.PaymentMode;
import enums.PaymentStatus;
import models.Payment;
import repositories.PaymentRepository;

public class CashPaymentStrategy implements PaymentStrategy {
    private final PaymentRepository paymentRepository;

    public CashPaymentStrategy() {
        paymentRepository = new PaymentRepository();
    }

    @Override
    public Payment pay(double amount) {
        Payment cashPayment = new Payment();

        cashPayment.setAmount(amount);
        cashPayment.setPaymentMode(PaymentMode.CASH);
        cashPayment.setPaymentStatus(PaymentStatus.SUCCESS);

        Payment savedCashPayment = paymentRepository.savePayment(cashPayment);
        savedCashPayment.setReferenceNumber("REFERENCE No. - " + savedCashPayment.getId());
        return savedCashPayment;
    }
}
