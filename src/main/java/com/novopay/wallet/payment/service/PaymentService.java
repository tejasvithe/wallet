package com.novopay.wallet.payment.service;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.dto.PaymentDetailsDto;
import com.novopay.wallet.dto.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public abstract class PaymentService {

    public abstract ResponseDto creditMoney(PaymentDetailsDto paymentDetails, Double amount);

    public abstract ResponseDto debitMoney(PaymentDetailsDto paymentDetails, Double amount);

    public abstract Double calculateCharge(Double amount);

    public Double calculateCommission(Double amount) {
        return (amount * ApplicationConstants.COMMISSION) / 100.0;
    }
}
