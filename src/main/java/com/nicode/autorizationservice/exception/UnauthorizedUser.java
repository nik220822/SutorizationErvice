package com.nicode.autorizationservice.exception;

public class UnauthorizedUser extends RuntimeException {

    public UnauthorizedUser(String msg) {
        super(msg);
    }
}
