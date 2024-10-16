package com.example.saltamar1001942895_parcial1;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int idMesa;
    private Mesa mesa;
    private List<Producto> productos;
    private double total;

    public Mesa(int idMesa) {
        this.idMesa = idMesa;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    public void cerrarVenta() {
        // Lógica para cerrar la venta y guardar los datos
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Mesa " + idMesa;
    }
}
