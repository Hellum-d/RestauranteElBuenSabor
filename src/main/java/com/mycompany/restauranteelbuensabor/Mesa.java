package com.mycompany.restauranteelbuensabor;
 
/**
 * Representa el estado de la mesa activa en el sistema.
 * Reemplaza los campos ms, est y tot de la clase Datos original.
 */
public class Mesa {
 
    private int numeroMesa;
    private boolean activa;
    private double totalUltimaFactura;
 
    public Mesa() {
        this.numeroMesa = 0;
        this.activa = false;
        this.totalUltimaFactura = 0;
    }
 
    public int getNumeroMesa() {
        return numeroMesa;
    }
 
    public void activar(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.activa = true;
    }
 
    public boolean isActiva() {
        return activa;
    }
 
    public double getTotalUltimaFactura() {
        return totalUltimaFactura;
    }
 
    public void setTotalUltimaFactura(double total) {
        this.totalUltimaFactura = total;
    }
 
    public void reiniciar() {
        this.numeroMesa = 0;
        this.activa = false;
        this.totalUltimaFactura = 0;
    }
}