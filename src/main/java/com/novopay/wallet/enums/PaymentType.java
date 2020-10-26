package com.novopay.wallet.enums;

public enum PaymentType {
    CREDIT_CARD("creditCard"), DEBIT_CARD("debitCard"), CARD_PAYMENT("cardPayment"), WALLET("Wallet");

    private final String value;

    private PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
