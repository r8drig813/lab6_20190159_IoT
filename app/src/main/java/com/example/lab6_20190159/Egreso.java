package com.example.lab6_20190159;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Egreso {
    private String titulo;
    private double monto;
    private String descripcion;
    private Date fecha;

    public Egreso(String titulo, double monto, String descripcion, Date fecha) {
        this.titulo = titulo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}