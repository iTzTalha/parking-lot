package strategies;

import enums.PaymentMode;
import enums.PaymentStatus;
import models.Payment;
import repositories.PaymentRepository;

public class CardPaymentStrategy implements PaymentStrategy {
    private final PaymentRepository paymentRepository;

    public CardPaymentStrategy() {
        paymentRepository = new PaymentRepository();
    }

    @Override
    public Payment pay(double amount) {
        Payment cardPayment = new Payment();

        cardPayment.setAmount(amount);
        cardPayment.setPaymentMode(PaymentMode.CARD);
        cardPayment.setPaymentStatus(PaymentStatus.SUCCESS);

        Payment savedCardPayment = paymentRepository.savePayment(cardPayment);
        savedCardPayment.setReferenceNumber("REFERENCE No. - " + savedCardPayment.getId());
        return savedCardPayment;
    }
}