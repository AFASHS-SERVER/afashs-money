package com.github.jaeukkang12.money.exceptions;

public class NegativeMoneyException extends RuntimeException{
    public NegativeMoneyException() {
    }

    public NegativeMoneyException(String msg) {
        super(msg);
    }
}
