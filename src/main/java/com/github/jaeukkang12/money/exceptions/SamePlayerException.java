package com.github.jaeukkang12.money.exceptions;

public class SamePlayerException extends RuntimeException {
    public SamePlayerException() {
    }

    public SamePlayerException(String msg) {
        super(msg);
    }
}
