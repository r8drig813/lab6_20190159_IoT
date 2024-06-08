package com.example.lab6_20190159;

import java.util.Date;

public class Ingreso {
    private String titulo;
    private double monto;
    private String descripcion;
    private String fecha;

    public Ingreso(String titulo, double monto, String descripcion, Date fecha) {
        this.titulo = titulo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = String.valueOf(fecha);
    }

    public Ingreso() {

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

    public String getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

