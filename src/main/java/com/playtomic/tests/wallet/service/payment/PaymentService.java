package com.playtomic.tests.wallet.service.payment;

import java.math.BigDecimal;

public interface PaymentService {
    Payment charge(String number, BigDecimal amount);
}
