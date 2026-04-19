package com.mycompany.restauranteelbuensabor;

/**
 * Coordina la generación de facturas y mantiene el contador de facturas.
 * Reemplaza las clases Proceso y Utilidades originales, eliminando:
 *   - hacerTodo() con múltiples responsabilidades
 *   - procesar() y calcular() que duplicaban la misma lógica con 7 parámetros
 *   - validar() con efecto secundario oculto que modificaba datos mientras validaba
 *   - El cálculo de totales duplicado 3 veces en distintas clases
 */
public class ServicioFacturacion {

    private int numeroFacturaSiguiente = 1;

    public boolean hayProductosEnPedido(Pedido pedido) {
        return pedido.tieneProductos();
    }

    /**
     * Crea una factura a partir del pedido activo y avanza el contador.
     */
    public Factura generarFactura(Pedido pedido) {
        Factura factura = new Factura(pedido, numeroFacturaSiguiente);
        numeroFacturaSiguiente++;
        return factura;
    }

    public int getNumeroFacturaSiguiente() {
        return numeroFacturaSiguiente;
    }
}
