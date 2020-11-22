package com.box.HRApiRest.Exceptions;

public class MarcaNotFoundException extends RuntimeException {

    public MarcaNotFoundException(Long id) {
        super("Could not find Marca " + id);
    }
}