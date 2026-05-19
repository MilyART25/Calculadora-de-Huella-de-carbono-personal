package uees.CalculadoraCO2rep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraApp {

    //se le pide un número al usuario y repite hasta tener uno
    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada= sc.nextLine().trim();
            try {
                double valor= Double.parseDouble(entrada);
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, ingrese un número (ejemplo: 12.5)");
            }
        }
    }

    //pide un entero y repite hasta obtener uno válido
    private static int leerInt(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada= sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, ingrese un número entero.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean salir= false;

        //lista que acumula todas las entradas para generar el reporte al salir
        List<Reportable> registros= new ArrayList<>();

        while (!salir) {
            System.out.println("\n_____________________________________________");
            System.out.println("  Calculadora de huella de carbono personal");
            System.out.println("_____________________________________________");
            System.out.println("\n   MENÚ PRINCIPAL   ");
            System.out.println("1. Transporte");
            System.out.println("2. Vivienda (Electricidad)");
            System.out.println("3. Alimentación");
            System.out.println("4. Consumo (Ropa)");
            System.out.println("5. Ver resumen de sesión");
            System.out.println("6. Salir y guardar reporte");

            int opcion = leerInt(sc, "Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    System.out.println("Tipo de vehículo (CARRO / MOTO / BUS / MICROBUS): ");
                    String vehiculo = sc.nextLine().trim();
                    System.out.println("Tipo de combustible (GASOLINA / DIESEL / ELECTRICO): ");
                    String combustible = sc.nextLine().trim();
                    double km = leerDouble(sc, "Kilómetros recorridos: ");
                    try {
                        Transporte t = new Transporte(km, vehiculo, combustible);
                        registros.add(t);
                        System.out.println("Registrado: " + t.obtenerDatosFila());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    double consumo = leerDouble(sc, "Consumo eléctrico en kWh: ");
                    try {
                        Vivienda v = new Vivienda(consumo);
                        registros.add(v);
                        System.out.println("Registrado: " + v.obtenerDatosFila());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Tipo de dieta (ALTA_CARNE / MEDIA / VEGETARIANA): ");
                    String dieta = sc.nextLine().trim();
                    double comidas = leerDouble(sc, "Número de comidas por semana: ");
                    try {
                        Alimentacion a = new Alimentacion(dieta, comidas);
                        registros.add(a);
                        System.out.println("Registrado: " + a.obtenerDatosFila());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Opciones: NUEVA_IMPORTADA / NUEVA_NACIONAL / SEGUNDA_MANO");
                    System.out.print("Tipo de consumo: ");
                    String tipoConsumo = sc.nextLine().trim();
                    double prendas = leerDouble(sc, "Cantidad de prendas: ");
                    try {
                        Consumo c = new Consumo(tipoConsumo, prendas);
                        registros.add(c);
                        System.out.println("Registrado: " + c.obtenerDatosFila());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    //resumen de la sesión actual
                    if (registros.isEmpty()) {
                        System.out.println("No se encontraron registros en esta sesión.");
                    } else {
                        double totalEmisiones = 0;
                        System.out.println("\n   RESUMEN DE SESIÓN   ");
                        for (Reportable r : registros) {
                            System.out.println("  " + r.obtenerDatosFila());
                            //extraer el último campo para sumar
                            String fila = r.obtenerDatosFila();
                            String[] partes = fila.split(",");
                            try {
                                totalEmisiones += Double.parseDouble(partes[partes.length - 1].trim());
                            } catch (NumberFormatException ignored) {}
                        }
                        System.out.printf("Total de emisiones: %.2f kgCO2%n", totalEmisiones);

                        //Mostrara recomendaciones segun la suma que hay dentro de esa sesion
                        Recomendacion.mostrar(totalEmisiones);
                    }
                    break;

                case 6:
                    salir = true;
                    //ahora se llama a GestorArchivos para generar el reporte
                    GestorArchivos.guardarReporte(registros);
                    System.out.println("Gracias por usar la calculadora de huella de carbono.");
                    break;

                default:
                    System.out.println("Opción inválida, elije entre 1 y 6.");
            }
        }

        sc.close();
    }
}
