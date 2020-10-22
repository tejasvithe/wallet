package com.novopay.wallet.payment.service.impl;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.dto.PaymentDetailsDto;
import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.payment.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class DebitCardService extends PaymentService {

    @Override
    public ResponseDto creditMoney(PaymentDetailsDto paymentDetails, Double amount) {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode(ApplicationConstants.SUCCESS);
        responseDto.setReason("money  credit in Debit Card");
        return responseDto;
    }

    @Override
    public ResponseDto debitMoney(PaymentDetailsDto paymentDetails, Double amount) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode(ApplicationConstants.SUCCESS);
        responseDto.setReason("money debit in Debit Card");
        return responseDto;
    }

    @Override
    public Double calculateCharge(Double amount) {
        return (amount * ApplicationConstants.DEBIT_CARD_CHARGE) / 100.0;
    }
}
