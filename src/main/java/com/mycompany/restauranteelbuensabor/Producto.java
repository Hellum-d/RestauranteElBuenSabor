package com.mycompany.restauranteelbuensabor;
 
/**
 * Representa un producto disponible en la carta del restaurante.
 * Reemplaza los arrays paralelos nom[] y p[] de la clase Datos original.
 */
public class Producto {
 
    private final String nombre;
    private final double precio;
 
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public double getPrecio() {
        return precio;
    }
}
 