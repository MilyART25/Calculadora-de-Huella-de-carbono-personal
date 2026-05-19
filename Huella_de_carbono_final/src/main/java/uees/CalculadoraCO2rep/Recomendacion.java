package uees.CalculadoraCO2rep;

public class Recomendacion {
    public static void mostrar(double totalCO2) {
        String nivel = determinarNivel(totalCO2);
        System.out.println("===================================\n");
        System.out.println(" RECOMENDACIONES");
        System.out.printf("Tu huella total es: %.2f kgCO2%n%n", totalCO2);
        System.out.println("===================================");

        switch (nivel) {
            case "ALTA":
                System.out.println("\n******** Tu huella es ALTA ********");
                System.out.println("Te recomendamos:");
                System.out.println("  - La movilidad activa (Caminar o usar bicicleta)");
                System.out.println("  - Reducir el consumo de carne roja");
                System.out.println("  - Revisar el consumo eléctrico del hogar");
                break;

            case "MEDIA":
                System.out.println("******** Tu huella es MODERADA ********");
                System.out.println("Te recomendamos:");
                System.out.println("  - Apagar dispositivos que no uses");
                System.out.println("  - Reducir el uso del agua");
                System.out.println("  - Comprar ropa de segunda mano o nacional");
                break;

            case "BAJA":
                System.out.println("******** Tu huella es BAJA ********");
                System.out.println("¡Sigue así! Te recomendamos:");
                System.out.println("  - Mantén tus hábitos actuales ");
                System.out.println("  - Recuerda las 3 R (Reducir, Reutilizar y Reciclar)");
                System.out.println("  - Comparte tus hábitos con otros");
                break;
        }
    }

    private static String determinarNivel(double co2) {
        if (co2 > 416) return "ALTA";
        else if (co2 >= 166) return "MEDIA";
        else return "BAJA";
    }
}
