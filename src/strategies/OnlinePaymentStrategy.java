package strategies;

import enums.PaymentMode;
import enums.PaymentStatus;
import models.Payment;
import repositories.PaymentRepository;

public class OnlinePaymentStrategy implements PaymentStrategy {
    private final PaymentRepository paymentRepository;

    public OnlinePaymentStrategy() {
        paymentRepository = new PaymentRepository();
    }

    @Override
    public Payment pay(double amount) {
        Payment onlinePayment = new Payment();

        onlinePayment.setAmount(amount);
        onlinePayment.setPaymentMode(PaymentMode.ONLINE);
        onlinePayment.setPaymentStatus(PaymentStatus.SUCCESS);

        Payment savedOnlinePayment = paymentRepository.savePayment(onlinePayment);
        savedOnlinePayment.setReferenceNumber("REFERENCE No. - " + savedOnlinePayment.getId());
        return savedOnlinePayment;
    }
}