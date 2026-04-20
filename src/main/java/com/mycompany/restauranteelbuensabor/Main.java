package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 * Punto de entrada del sistema de facturación del restaurante.
 * Solo orquesta el menú — toda la lógica de negocio vive en las demás clases.
 *
 * Correcciones respecto al Main original:
 *   - El Scanner sc2 eliminado — solo existe un Scanner para System.in
 *   - La variable continuar eliminada — nunca se usaba para tomar decisiones
 *   - Las variables aux, tmp, m, x eliminadas — eran copias innecesarias de datos
 *   - La lógica de cálculo extraída a ServicioFacturacion y Factura
 *   - El método main dividido en métodos auxiliares con una sola responsabilidad
 */
public class Main {

    private static final Carta carta = new Carta();
    private static final Mesa mesa = new Mesa();
    private static final Pedido pedido = new Pedido();
    private static final ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        imprimirBienvenida();

        boolean ejecutando = true;
        while (ejecutando) {
            imprimirMenu();
            int opcionMenu = scanner.nextInt();
            ejecutando = procesarOpcion(opcionMenu, scanner);
        }

        scanner.close();
    }

    private static boolean procesarOpcion(int opcionMenu, Scanner scanner) {
        switch (opcionMenu) {
            case 1:
                FacturaImpresor.mostrarCarta(carta);
                break;
            case 2:
                procesarAgregarProducto(scanner);
                break;
            case 3:
                procesarVerPedido();
                break;
            case 4:
                procesarGenerarFactura();
                break;
            case 5:
                procesarNuevaMesa();
                break;
            case 0:
                System.out.println("Hasta luego!");
                return false;
            default:
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
        }
        System.out.println();
        return true;
    }

    private static void procesarAgregarProducto(Scanner scanner) {
        System.out.println("--- AGREGAR PRODUCTO ---");
        System.out.print("Numero de producto (1-" + carta.getTotalProductos() + "): ");
        int numeroProducto = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        Producto productoSeleccionado = carta.getProducto(numeroProducto);
        if (productoSeleccionado == null) {
            if (numeroProducto <= 0) {
                System.out.println("El numero debe ser mayor a cero.");
            } else {
                System.out.println("Producto no existe. La carta tiene " + carta.getTotalProductos() + " productos.");
            }
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser un valor positivo.");
            return;
        }

        if (!mesa.isActiva()) {
            System.out.print("Ingrese numero de mesa: ");
            int numeroMesa = scanner.nextInt();
            mesa.activar(numeroMesa > 0 ? numeroMesa : 1);
        }

        pedido.agregarProducto(productoSeleccionado, cantidad);
        System.out.println("Producto agregado al pedido.");
        System.out.println("  -> " + productoSeleccionado.getNombre() + " x" + cantidad);
    }

    private static void procesarVerPedido() {
        System.out.println();
        if (servicioFacturacion.hayProductosEnPedido(pedido)) {
            FacturaImpresor.mostrarPedido(pedido);
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
    }

    private static void procesarGenerarFactura() {
        System.out.println();
        if (!servicioFacturacion.hayProductosEnPedido(pedido)) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }

        Factura factura = servicioFacturacion.generarFactura(pedido);
        mesa.setTotalUltimaFactura(factura.calcularTotal());
        FacturaImpresor.imprimirFacturaCompleta(factura);
        pedido.limpiar();
        mesa.reiniciar();
    }

    private static void procesarNuevaMesa() {
        System.out.println();
        pedido.limpiar();
        mesa.reiniciar();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
    }

    private static void imprimirBienvenida() {
        System.out.println("========================================");
        System.out.println("    RESTAURANTE El Buen Sabor");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");
    }

    private static void imprimirMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }
} 
