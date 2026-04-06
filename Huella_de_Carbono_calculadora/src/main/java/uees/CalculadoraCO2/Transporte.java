package uees.CalculadoraCO2;

public class Transporte extends CategoriaImpacto{
    //Aquí van definidos los factores de emisión

    private double kmRecorridos;
    //No he agregado TipoVehiculo
    //*TipoVehiculo*
    private String tipoVehiculo;
    private String tipoCombustible;

// Factores de emision (kgCO2 por KM) DEPENDIENDO DEL TIPO DE COMBUSTIBLE
    //ejemplo:
    private final double Fe_Gasolina_Carro = 0.25; // kgCO2 por km en un vehículo particular
    private final double Fe_Diesel_Carro = 0.30; // Vehiculo con uso diesel
    private final double Fe_Electrico_Carro = 0.05; // electrico

    private final double Fe_Gasolina_Moto = 0.15;
    private final double Fe_Diesel_Bus = 0.50;
    private final double Fe_Diesel_Microbus = 0.40;

    public Transporte(double kmRecorridos, String tipoVehiculo, String tipoCombustible) {
        super("Transporte", kmRecorridos);
        this.kmRecorridos = kmRecorridos;
        this.tipoVehiculo = tipoVehiculo.toUpperCase(); //Se utiliza .toUpperCase() para que si el usuario escribe carro, Carro,CARRO es sistema lo transforme he entienda
        this.tipoCombustible = tipoCombustible.toUpperCase();
    }

    @Override
    public double calcularEmision(){

        if (tipoVehiculo.equals("CARRO")) {
            if (tipoCombustible.equals("GASOLINA"))
                return kmRecorridos * Fe_Gasolina_Carro;
            if (tipoCombustible.equals("DIESEL"))
                return kmRecorridos * Fe_Diesel_Carro;
            if (tipoCombustible.equals("ELECTRONICO"))
                return kmRecorridos * Fe_Electrico_Carro;
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
        return 0.0; // Si no coincide nada, regresa 0.0


    }

    //Agregamos obtenerDatosFila para el reporte csv
    @Override
    public String obtenerDatosFila(){
        return fecha + ",Transporte,"+ tipoVehiculo + "," + tipoCombustible + "," + kmRecorridos + "km,"+ calcularEmision();
    }
}
