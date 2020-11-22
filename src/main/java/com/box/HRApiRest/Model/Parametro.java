package com.box.HRApiRest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Parametro {

    private @Id @GeneratedValue Long id;

    private String nombre;
    private String descripcion;
    private String valorTexto;
    private Integer valorInt;
    private Long valorLong;
    private Date valorFecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public Integer getValorInt() {
        return valorInt;
    }

    public void setValorInt(Integer valorInt) {
        this.valorInt = valorInt;
    }

    public Long getValorLong() {
        return valorLong;
    }

    public void setValorLong(Long valorLong) {
        this.valorLong = valorLong;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }
}

