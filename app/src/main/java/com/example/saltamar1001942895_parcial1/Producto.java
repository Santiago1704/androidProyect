package com.example.saltamar1001942895_parcial1;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String tipo;

    public Producto(String nombre, double precio, String categoria, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return nombre;
    }
}
