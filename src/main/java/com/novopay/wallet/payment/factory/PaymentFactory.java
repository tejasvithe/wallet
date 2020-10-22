package com.novopay.wallet.payment.factory;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.payment.service.PaymentService;
import com.novopay.wallet.payment.service.impl.CreditCardService;
import com.novopay.wallet.payment.service.impl.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private DebitCardService debitCardService;

    public PaymentService getPaymentService(String paymentType) {
        PaymentService paymentService;

        switch (paymentType) {

            case ApplicationConstants
                    .CREDIT_CARD:
                paymentService = creditCardService;
                break;

            case ApplicationConstants.DEBIT_CARD:
                paymentService = debitCardService;
                break;

            default:
                paymentService = null;
                break;
        }
        return paymentService;


    }
}
