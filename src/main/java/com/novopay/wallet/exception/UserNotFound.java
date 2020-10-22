package com.novopay.wallet.exception;

public class UserNotFound extends RuntimeException {

    public UserNotFound(String msg) {
        super(msg);
    }
}
