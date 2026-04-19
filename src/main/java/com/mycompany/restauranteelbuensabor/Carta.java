package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

/**
 * Almacena los productos disponibles en el restaurante.
 * Reemplaza los arrays nom[] y p[] de la clase Datos original.
 */
public class Carta {

    private final List<Producto> productos = new ArrayList<>();

    public Carta() {
        productos.add(new Producto("Bandeja Paisa",       32000));
        productos.add(new Producto("Sancocho de Gallina", 28000));
        productos.add(new Producto("Arepa con Huevo",      8000));
        productos.add(new Producto("Jugo Natural",         7000));
        productos.add(new Producto("Gaseosa",              4500));
        productos.add(new Producto("Cerveza Poker",        6000));
        productos.add(new Producto("Agua Panela",          3500));
        productos.add(new Producto("Arroz con Pollo",     25000));
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public int getTotalProductos() {
        return productos.size();
    }

    /**
     * Retorna el producto por su número de carta (base 1).
     * Retorna null si el número está fuera de rango.
     */
    public Producto getProducto(int numeroCarta) {
        if (numeroCarta < 1 || numeroCarta > productos.size()) {
            return null;
        }
        return productos.get(numeroCarta - 1);
    }
}