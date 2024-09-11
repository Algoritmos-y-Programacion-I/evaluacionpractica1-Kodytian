package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {
        System.out.println("Bienvenido a Guacamaya!");
        establecerCantidadVendida();
        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: " + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, " + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nOpción inválida, por favor intente de nuevo.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();
        precios = new double[referencias];
        unidades = new int[referencias];
    }

    /**
     * Descripción: Este método se encarga de preguntarle al usuario el precio y la cantidad de cada referencia existente. 
     * pre: El Scanner reader debe estar inicializado. 
     * pre: Los arreglos precios y unidades deben estar declarados y ya tiene que estar establecida su longitud a través del método establecerCantidadVendida().
     * pos: Se llenan todos los campos de los arreglos precios y unidades. 
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("\nDigíte la cantidad de unidades vendidas de la referencia N°" + (i + 1) + ".");
            unidades[i] = reader.nextInt();

            System.out.println("\nDigíte el precio de una unidad de la referencia N°" + (i + 1) + ".");
            precios[i] = reader.nextDouble();
        }
    }

    /**
     * Descripción: Este método calcula la suma total de unidades de productos vendidos de todas las referencias existentes.
     * @return int total: Es el total de la suma de todas las unidades de los productos vendidos de todas las referencias existentes.
     * pre: El arreglo unidades debe estar declarado y ya tiene que estar establecida su longitud a través del método establecerCantidadVendida().
     * pre: Todos los campos del arreglo unidades deben estar llenos.
     */
    public static int calcularTotalUnidadesVendidas() {
        int total = 0;
        for (int i = 0; i < unidades.length; i++) {
            total += unidades[i];
        }
        return total;
    }

    /**
     * Descripción: Este método calcula el promedio de los precios de las referencias almacenadas en el sistema anteriormente en el método solicitarDatos().
     * pre: El arreglo precios debe estar declarado y ya tiene que estar establecida su longitud a través del método establecerCantidadVendida().
     * pre: Todos los campos del arreglo precios deben estar llenos. 
     * @return promedio: Es el resultado del cálculo del promedio de los precios de todas las referencias.
     */
    public static double calcularPrecioPromedio() {
        double total = 0;
        for (int i = 0; i < precios.length; i++) {
            total += precios[i];
        }
        return total / precios.length;
    }

    /** 
     * Descripción: Este método calcula las ventas totales de todas las referencias y luego las suma.
     * pre: Los arreglos precios y unidades deben estar declarados y ya tiene que estar establecida su longitud a través del método establecerCantidadVendida().
     * pre: Todos los campos del arreglo precios y unidades deben estar llenos.
     * @return total: Son las ganancias que obtiene la tienda por todas las ventas que realizó en el día.
    */
    public static double calcularVentasTotales() {
        double total = 0;
        for (int i = 0; i < unidades.length; i++) {
            total += unidades[i] * precios[i];
        }
        return total;
    }

    /**
     * Descripción: Este método realiza una operación para ver cuáles de las referencias de productos que en el día hayan superado un límite mínimo de ventas proporcionado por el usuario.
     * pre: Los arreglos precios y unidades deben estar declarados y ya tiene que estar establecida su longitud a través del método establecerCantidadVendida().
     * pre: Todos los campos del arreglo precios y unidades deben estar llenos.
     * pre: La variable limite debe estar definida y debe contener ya un valor que se ingresó anteriormente.
     * @param limite double : Es el límite mínimo de ventas.
     * @return unidadesQuePasaron: Es la variable que almacena la cantidad de productos que superaron el límite mínimo de ventas.
     */
    public static int consultarReferenciasSobreLimite(double limite) {
        int unidadesQuePasaron = 0;
        for (int i = 0; i < precios.length; i++) {
            double totalVenta = precios[i] * unidades[i];
            if (totalVenta >= limite) {
                unidadesQuePasaron++;
            }
        }
        return unidadesQuePasaron;
    }
}
