package uees.CalculadoraCO2rep;

public class Alimentacion extends CategoriaImpacto {
    private String tipoDieta;
    private double frecuenciaCarne;

    private final double Fe_carneRes = 6.16;
    private final double Fe_Media = 3.69;
    private final double Fe_Vegetariana = 1.78;

    public Alimentacion(String tipoDieta, double frecuenciaCarneRes) {
        super("Alimentación", frecuenciaCarneRes);

        //frecuencia debe ser positiva
        if (frecuenciaCarneRes <= 0) {
            throw new IllegalArgumentException("La frecuencia de comidas debe ser mayor a 0");
        }

        this.tipoDieta = tipoDieta.toUpperCase().replace("_", " ");
        this.frecuenciaCarne = frecuenciaCarneRes;

        //tipo de dieta debe ser reconocido
        if (!this.tipoDieta.equals("ALTA CARNE") &&
            !this.tipoDieta.equals("MEDIA") &&
            !this.tipoDieta.equals("VEGETARIANA")) {
            throw new IllegalArgumentException(
                "Tipo de dieta no válido: \"" + tipoDieta + "\". " +
                "Use: ALTA_CARNE, MEDIA o VEGETARIANA."
            );
        }
    }

    @Override
    public double calcularEmision() {
        if (tipoDieta.equals("ALTA CARNE")) {
            return frecuenciaCarne * Fe_carneRes;
        } else if (tipoDieta.equals("MEDIA")) {
            return frecuenciaCarne * Fe_Media;
        } else if (tipoDieta.equals("VEGETARIANA")) {
            return frecuenciaCarne * Fe_Vegetariana;
        }
        return 0.0;
    }

    @Override
    public String obtenerDatosFila() {
        return fecha + ", Alimentacion, " + tipoDieta + ", " + frecuenciaCarne + " comidas, " + calcularEmision();
    }
}
