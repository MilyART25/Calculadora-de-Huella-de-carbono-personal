package uees.CalculadoraCO2rep;

public class Consumo extends CategoriaImpacto {
    private String tipoConsumo;
    private double cantidadPrendas;

    //los factores de emisión por prenda (kgCO2)
    private final double Fe_RopaImportada  = 3.12;
    private final double Fe_RopaNacional   = 3.12;
    private final double Fe_RopaSegundaMano = 0.37;

    public Consumo(String tipoConsumo, double cantidadPrendas) {
        super("Consumo en ropa", cantidadPrendas);

        //cantidad de prendas tiene q ser positiva
        if (cantidadPrendas <= 0) {
            throw new IllegalArgumentException("La cantidad de prendas debe ser mayor a 0.");
        }

        this.tipoConsumo     = tipoConsumo.toUpperCase();
        this.cantidadPrendas = cantidadPrendas;

        //tipo de consumo debe ser reconocido
        if (!this.tipoConsumo.equals("NUEVA_IMPORTADA") &&
            !this.tipoConsumo.equals("NUEVA_NACIONAL")  &&
            !this.tipoConsumo.equals("SEGUNDA_MANO")) {
            throw new IllegalArgumentException(
                "Tipo de consumo no válido: \"" + tipoConsumo + "\". " +
                "Use: NUEVA_IMPORTADA, NUEVA_NACIONAL o SEGUNDA_MANO."
            );
        }
    }

    @Override
    public double calcularEmision() {
        if (tipoConsumo.equals("NUEVA_IMPORTADA")) {
            return cantidadPrendas * Fe_RopaImportada;
        } else if (tipoConsumo.equals("NUEVA_NACIONAL")) {
            return cantidadPrendas * Fe_RopaNacional;
        } else if (tipoConsumo.equals("SEGUNDA_MANO")) {
            return cantidadPrendas * Fe_RopaSegundaMano;
        }
        return 0.0;
    }

    @Override
    public String obtenerDatosFila() {
        return fecha + ", Consumo, " + tipoConsumo + ", " + cantidadPrendas + " prendas, " + calcularEmision();
    }
}
