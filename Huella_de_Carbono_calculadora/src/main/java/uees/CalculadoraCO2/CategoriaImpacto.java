package uees.CalculadoraCO2;

import java.time.LocalDate;
//representar una fecha (año, mes y día) sin incluir información de la hora específica del día


public abstract class CategoriaImpacto implements Calculable, Reportable{
    //esta clase abstracta debe implementar las dos interfaces

    protected String nombre;
    protected double cantidad; // se refiere a km, kWh, kg, etc.
    protected LocalDate fecha;

    public CategoriaImpacto(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
    }
}
