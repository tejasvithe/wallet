package com.novopay.wallet.exception;

public class InvalidTransactionException extends RuntimeException {

    public InvalidTransactionException(String msg) {
        super(msg);
    }
}
