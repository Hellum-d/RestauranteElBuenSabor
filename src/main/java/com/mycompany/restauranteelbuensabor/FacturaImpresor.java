package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de imprimir en consola la carta, el pedido y las facturas.
 * No calcula nada — delega todos los cálculos a Factura.
 *
 * Reemplaza la clase Imprimir original, eliminando:
 *   - El cálculo duplicado de subtotal/IVA/propina que existía en ambos métodos
 *   - Los datos del restaurante hardcodeados 4 veces
 *   - El 80% de código idéntico entre imprimirFacturaCompleta e imprimirFacturaResumen
 */
public class FacturaImpresor {

    private static final String SEPARADOR_DOBLE = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";
    private static final String FORMATO_ITEM     = "%-20s x%-6d $%,.0f%n";
    private static final String FORMATO_LINEA    = "%-27s $%,.0f%n";

    private static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    private static final String DIRECCION          = "Calle 15 #8-32, Valledupar";
    private static final String NIT                = "900.123.456-7";

    public static void mostrarCarta(Carta carta) {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        for (int indice = 0; indice < carta.getTotalProductos(); indice++) {
            Producto producto = carta.getProducto(indice + 1);
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), producto.getNombre(), producto.getPrecio());
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido(Pedido pedido) {
        double subtotal = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf(FORMATO_ITEM, item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
            subtotal += item.calcularSubtotal();
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_LINEA, "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado(factura.getNumero());
        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf(FORMATO_ITEM, item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
        }
        imprimirTotales(factura);
        System.out.println("Gracias por su visita!");
        System.out.println(NOMBRE_RESTAURANTE + " - " + DIRECCION);
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado(factura.getNumero());
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);
        imprimirTotales(factura);
    }

    // -------------------------------------------------------------------------
    // Métodos privados de apoyo
    // -------------------------------------------------------------------------

    private static void imprimirEncabezado(int numeroFactura) {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println(SEPARADOR_DOBLE);
        System.out.printf("FACTURA No. %03d%n", numeroFactura);
        System.out.println(SEPARADOR_SIMPLE);
    }

    private static void imprimirTotales(Factura factura) {
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_LINEA, "Subtotal:",    factura.calcularSubtotalConDescuento());
        System.out.printf(FORMATO_LINEA, "IVA (19%):",   factura.calcularMontoIva());
        if (factura.calcularMontoPropina() > 0) {
            System.out.printf(FORMATO_LINEA, "Propina (10%):", factura.calcularMontoPropina());
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_LINEA, "TOTAL:", factura.calcularTotal());
        System.out.println(SEPARADOR_DOBLE);
    }
} 