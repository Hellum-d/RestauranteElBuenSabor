package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el pedido activo de una mesa.
 * Reemplaza los arrays cant[] y la lógica dispersa de Datos original.
 */
public class Pedido {

    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemPedido item : items) {
            if (item.getProducto().getNombre().equals(producto.getNombre())) {
                item.agregarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public void limpiar() {
        items.clear();
    }
}