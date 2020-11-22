package com.box.HRApiRest.Exceptions;

public class HorarioNotFoundException extends RuntimeException {

    public HorarioNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}