package uees.CalculadoraCO2;

import java.util.Scanner;

public class CalculadoraApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Transporte");
            System.out.println("2. Vivienda (Electricidad)");
            System.out.println("3. Alimentación");
            System.out.println("4. Consumo (Ropa)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar

            switch (opcion) {
                case 1:
                    System.out.print("Tipo de vehículo (CARRO/MOTO/BUS/MICROBUS): ");
                    String vehiculo = sc.nextLine();
                    System.out.print("Tipo de combustible (GASOLINA/DIESEL/ELECTRICO): ");
                    String combustible = sc.nextLine();
                    System.out.print("Kilómetros recorridos: ");
                    double km = sc.nextDouble();
                    Transporte t = new Transporte(km, vehiculo, combustible);
                    System.out.println("Resultado: " + t.obtenerDatosFila());
                    break;

                case 2:
                    System.out.print("Consumo eléctrico en kWh: ");
                    double consumo = sc.nextDouble();
                    Vivienda v = new Vivienda(consumo);
                    System.out.println("Resultado: " + v.obtenerDatosFila());
                    break;

                case 3:
                    System.out.print("Tipo de dieta (ALTA_CARNE/MEDIA/VEGETARIANA): ");
                    String dieta = sc.nextLine();
                    System.out.print("Número de comidas por semana: ");
                    double comidas = sc.nextDouble();
                    Alimentacion a = new Alimentacion(dieta, comidas);
                    System.out.println("Resultado: " + a.obtenerDatosFila());
                    break;

                case 4:
                    System.out.println("Opciones: NUEVA_IMPORTADA, NUEVA_NACIONAL, SEGUNDA_MANO");
                    System.out.print("Tipo de consumo: ");
                    String tipoConsumo = sc.nextLine();
                    System.out.print("Cantidad de prendas: ");
                    double prendas = sc.nextDouble();
                    Consumo c = new Consumo(tipoConsumo, prendas);
                    System.out.println("Resultado: " + c.obtenerDatosFila());
                    break;

                case 5:
                    salir = true;
                    System.out.println("Gracias por usar la calculadora de huella de carbono.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();

    }
}
