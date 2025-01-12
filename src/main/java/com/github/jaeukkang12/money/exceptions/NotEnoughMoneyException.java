package com.github.jaeukkang12.money.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
    }

    NotEnoughMoneyException(String message) {
        super(message);
    }
}
