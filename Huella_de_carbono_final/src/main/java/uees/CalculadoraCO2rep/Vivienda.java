package uees.CalculadoraCO2rep;

public class Vivienda extends CategoriaImpacto {

    private final double Fe_kWh= 0.20; // kgCO2 por kWh
    private double consumokWh;

    public Vivienda(double consumokwh) {
        super("Electricidad", consumokwh);

        //el consumo debe ser positivo
        if (consumokwh <= 0) {
            throw new IllegalArgumentException("El consumo eléctrico debe ser mayor a 0 kWh.");
        }

        this.consumokWh = consumokwh;
    }

    @Override
    public double calcularEmision() {
        return consumokWh * Fe_kWh;
    }

    @Override
    public String obtenerDatosFila() {
        // arreglo del formato de vista del reporte
        return fecha + ", Vivienda, " + consumokWh + " kWh, " + calcularEmision();
    }
}
