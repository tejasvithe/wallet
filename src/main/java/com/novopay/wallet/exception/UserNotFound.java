package com.novopay.wallet.exception;

public class UserNotFound extends RuntimeException {

    public UserNotFound(String msg) {
        super(msg);
    }

    public UserNotFound(String msg, Exception e) {
        super(msg, e);
    }
}
