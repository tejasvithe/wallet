package com.novopay.wallet.exception;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String msg) {
        super(msg);
    }
}
