package com.mycompany.restauranteelbuensabor;

/**
 * Encapsula el cálculo de una factura a partir de un pedido.
 * Solo calcula — la impresión la realiza FacturaImpresor.
 *
 * Reglas de negocio:
 *   - Descuento 5%: aplica si hay más de 3 productos diferentes en el pedido.
 *   - IVA 19%: se calcula sobre el subtotal después del descuento.
 *   - Propina 10%: aplica si el subtotal (antes del IVA) supera $50.000.
 *   - Total = subtotal con descuento + IVA + propina.
 */
public class Factura { 

    // Constantes de negocio — un solo lugar para cambiar tarifas
    private static final double TASA_IVA            = 0.19;
    private static final double TASA_PROPINA        = 0.10;
    private static final double TASA_DESCUENTO      = 0.05;
    private static final double UMBRAL_PROPINA      = 50000;
    private static final int    MIN_ITEMS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public double calcularSubtotalBruto() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return calcularSubtotalBruto() * TASA_DESCUENTO;
        }
        return 0;
    }

    public double calcularSubtotalConDescuento() {
        return calcularSubtotalBruto() - calcularDescuento();
    }

    public double calcularMontoIva() {
        // El IVA se calcula sobre el subtotal ya descontado, según DIAN 2024
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    public double calcularMontoPropina() {
        // La propina aplica sobre el total con IVA incluido, solo si el subtotal supera el umbral
        double subtotalConDescuento = calcularSubtotalConDescuento();
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            return (subtotalConDescuento + calcularMontoIva()) * TASA_PROPINA;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotalConDescuento() + calcularMontoIva() + calcularMontoPropina();
    }
}
