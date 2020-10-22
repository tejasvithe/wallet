package com.novopay.wallet.enums;

public enum PaymentType {
    CREDIT_CARD("Credit Card"), DEBIT_CARD("Debit Card"), CARD_PAYMENT("Card Payment"), WALLET("Wallet");

    private final String value;

    private PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
