package uees.CalculadoraCO2rep;

public class Transporte extends CategoriaImpacto {

    private double kmRecorridos;
    private String tipoVehiculo;
    private String tipoCombustible;

    //factores de emisión //    Algunos se actualizaron
    private final double Fe_Gasolina_Carro= 0.25;
    private final double Fe_Diesel_Carro= 0.30;
    private final double Fe_Electrico_Carro= 0.05;
    private final double Fe_Gasolina_Moto= 0.15;
    private final double Fe_Diesel_Bus= 0.40;
    private final double Fe_Diesel_Microbus= 0.70;

    public Transporte(double kmRecorridos, String tipoVehiculo, String tipoCombustible) {
        super("Transporte", kmRecorridos);

        //km deben ser positivos
        if (kmRecorridos <= 0) {
            throw new IllegalArgumentException("Los kilómetros recorridos deben ser mayores a 0.");
        }

        this.kmRecorridos= kmRecorridos;
        this.tipoVehiculo= tipoVehiculo.toUpperCase();
        this.tipoCombustible= tipoCombustible.toUpperCase();

        //validación de vehículo-combustible
        if (!esCombinacionValida()) {
            throw new IllegalArgumentException(
                "Combinación no válida: \"" + tipoVehiculo + "\" con \"" + tipoCombustible + "\". " +
                "Opciones: CARRO(GASOLINA/DIESEL/ELECTRICO), MOTO(GASOLINA), BUS(DIESEL), MICROBUS(DIESEL)"
            );
        }
    }

    //verifica que la combinación vehículo + combustible
    private boolean esCombinacionValida() {
        switch (tipoVehiculo) {
            case "CARRO":
                return tipoCombustible.equals("GASOLINA") ||
                       tipoCombustible.equals("DIESEL")   ||
                       tipoCombustible.equals("ELECTRICO");
            case "MOTO":
                return tipoCombustible.equals("GASOLINA");
            case "BUS":
            case "MICROBUS":
                return tipoCombustible.equals("DIESEL");
            default:
                return false;
        }
    }

    @Override
    public double calcularEmision() {
        if (tipoVehiculo.equals("CARRO")) {
            if (tipoCombustible.equals("GASOLINA"))
                return kmRecorridos * Fe_Gasolina_Carro;
            if (tipoCombustible.equals("DIESEL"))
                return kmRecorridos * Fe_Diesel_Carro;
            if (tipoCombustible.equals("ELECTRICO"))
                return kmRecorridos * Fe_Electrico_Carro; // FIX
        } else if (tipoVehiculo.equals("MOTO")) {
            if (tipoCombustible.equals("GASOLINA"))
                return kmRecorridos * Fe_Gasolina_Moto;
        } else if (tipoVehiculo.equals("BUS")) {
            if (tipoCombustible.equals("DIESEL"))
                return kmRecorridos * Fe_Diesel_Bus;
        } else if (tipoVehiculo.equals("MICROBUS")) {
            if (tipoCombustible.equals("DIESEL"))
                return kmRecorridos * Fe_Diesel_Microbus;
        }
        return 0.0;
    }

    @Override
    public String obtenerDatosFila() {
        return fecha + ", Transporte, " + tipoVehiculo + ", " + tipoCombustible + ", " + kmRecorridos + " km, " + calcularEmision();
    }
}
