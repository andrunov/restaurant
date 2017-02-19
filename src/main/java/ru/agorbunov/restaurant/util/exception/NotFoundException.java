package ru.agorbunov.restaurant.util.exception;

/**
 * Created by Admin on 19.02.2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
