package com.box.HRApiRest.Exceptions;

public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(Long id) {
        super("Could not find Producto " + id);
    }
}