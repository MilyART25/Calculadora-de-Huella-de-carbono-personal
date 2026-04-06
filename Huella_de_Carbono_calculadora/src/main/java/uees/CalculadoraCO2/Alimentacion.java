package uees.CalculadoraCO2;

public class Alimentacion extends CategoriaImpacto{
    private String tipoDieta;
    private double frecuenciaCarne;
    //hay que definir bien el factor de la alimentacions
    private final double Fe_carneRes= 27.0;
    private final double Fe_Media = 3.0;
    private final double Fe_Vegetariana = 1.5;

    public Alimentacion(String tipoDieta, double frecuenciaCarneRes){
        super("Alimentación", frecuenciaCarneRes);
        this.tipoDieta = tipoDieta.toUpperCase();
        this.frecuenciaCarne = frecuenciaCarneRes;
    }

    //implementamos las interfaces

    @Override
    public double calcularEmision() {
        if (tipoDieta.equals("ALTA_CARNE")) {
            return frecuenciaCarne * Fe_carneRes;
        } else if (tipoDieta.equals("MEDIA")) {
            return frecuenciaCarne * Fe_Media;
        } else if (tipoDieta.equals("VEGETARIANA")) {
            return frecuenciaCarne * Fe_Vegetariana;
        }
        return 0.0; // si no coincide nada

    }

    @Override
    public String obtenerDatosFila() {

        return fecha + ",Alimentacion, " + tipoDieta + "," + frecuenciaCarne + " comidas, " + calcularEmision();
    }
}
