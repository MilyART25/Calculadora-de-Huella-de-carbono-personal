package uees.CalculadoraCO2;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class GestorArchivos {
    public static void guardarReporte(List<Reportable> lista){
        try (PrintWriter writer= new PrintWriter(new FileWriter("reporte_huella-csv", true))){
            for (Reportable item: lista){
                writer.println(item.obtenerDatosFila());
            }
            System.out.println("Archivo guardado con éxito.");
        }catch (Exception e){
            System.out.println("Error al guardar: "+ e.getMessage());
        }
    }
}
