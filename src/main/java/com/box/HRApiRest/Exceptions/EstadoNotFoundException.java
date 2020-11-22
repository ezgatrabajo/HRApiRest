package com.box.HRApiRest.Exceptions;

public class EstadoNotFoundException extends RuntimeException {

    public EstadoNotFoundException(Long id) {
        super("Could not find Estado " + id);
    }
}