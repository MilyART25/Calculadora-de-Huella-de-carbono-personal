package uees.CalculadoraCO2;

public class Consumo extends CategoriaImpacto{
    private String tipoConsumo;
    private double cantidadPrendas;

//factores de ropa
    private final double Fe_RopaImportada= 15.0; //kgCO2 por prenda
    private final double Fe_RopaNacional = 10.0;
    private final double Fe_RopaSegundaMano = 4.0;

    public Consumo(String tipoConsumo, double cantidadPrendas){

        super("Consumo en ropa", cantidadPrendas);
        this.tipoConsumo = tipoConsumo.toUpperCase();
        this.cantidadPrendas = cantidadPrendas;
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
        return 0.0; // si no coincide nada

    }

    @Override
    public String obtenerDatosFila() {

        return fecha + ", Consumo, " + tipoConsumo + ", " + cantidadPrendas + " prendas," + calcularEmision();
    }
}
