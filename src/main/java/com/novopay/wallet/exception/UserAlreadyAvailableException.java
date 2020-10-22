package com.novopay.wallet.exception;

public class UserAlreadyAvailableException extends RuntimeException {
    public UserAlreadyAvailableException(String msg) {
        super(msg);
    }
}
