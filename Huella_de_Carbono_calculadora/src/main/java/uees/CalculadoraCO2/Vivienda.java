package uees.CalculadoraCO2;

public class Vivienda extends CategoriaImpacto{
    //ver la clase Transporte para entender la lógica
    private final double Fe_kWh= 0.20; // revisar el valor
    private double consumokWh;

    public Vivienda(double consumokwh){
        super("Electricidad", consumokwh);
        this.consumokWh = consumokwh;
    }

    @Override
    public double calcularEmision() {
        return consumokWh * Fe_kWh;
    }

    @Override
    public String obtenerDatosFila() {

        return fecha + "Vivienda," + consumokWh + "kWh, " + calcularEmision();
    }
}
