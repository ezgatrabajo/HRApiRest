package com.box.HRApiRest.Exceptions;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}