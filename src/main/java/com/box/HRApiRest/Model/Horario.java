package com.box.HRApiRest.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Horario {

    private @Id @GeneratedValue Long id;

    private Integer dia;

    private Date apertura;

    private Date cierre;

    private String observaciones;

    public Horario(Long id, Integer dia, Date apertura, Date cierre, String observaciones) {
        this.id = id;
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
        this.observaciones = observaciones;
    }

    public Horario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

