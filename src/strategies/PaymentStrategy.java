package strategies;

import models.Payment;

public interface PaymentStrategy {
    Payment pay(double amount);
}
