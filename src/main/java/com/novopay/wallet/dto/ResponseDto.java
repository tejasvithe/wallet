package com.novopay.wallet.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private String resultCode;

    private String reason;

    private String incorrectFields;

    private String transactionId;
}
