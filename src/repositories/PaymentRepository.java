package repositories;

import models.Payment;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class PaymentRepository {
    private final Map<Long, Payment> paymentMap;
    private Long previousId = 0L;

    public PaymentRepository() {
        paymentMap = new TreeMap<>();
    }

    public Payment savePayment(Payment payment) {
        previousId++;
        payment.setId(previousId);
        payment.setCreatedAt(new Date());
        paymentMap.put(previousId, payment);
        return payment;
    }
}
