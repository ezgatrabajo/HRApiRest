package com.box.HRApiRest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producto {

    private @Id @GeneratedValue Long id;

    private String nombre;

    private String descripcion;

    private String codigoexterno;

    private Float stock;

    private Float preciounitario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }

    public Producto(int id, String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Producto() {
        super();
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

}

