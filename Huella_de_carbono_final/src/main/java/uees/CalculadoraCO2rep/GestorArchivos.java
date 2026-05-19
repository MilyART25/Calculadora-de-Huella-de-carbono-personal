package uees.CalculadoraCO2rep;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.util.List;

public class GestorArchivos {

    //el nombre del archivo tenía un guión en vez de punto antes de "csv"
    private static final String NOMBRE_ARCHIVO = "reporte_huella.csv";

    public static void guardarReporte(List<Reportable> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay datos para guardar en el reporte.");
            return;
        }

        //por si el archivo no existe aún escribir el encabezado CSV
        boolean archivoNuevo = !new File(NOMBRE_ARCHIVO).exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            if (archivoNuevo) {
                writer.println("Fecha, Categoria, Detalle1, Detalle2, Cantidad, Emision_kgCO2");
            }
            for (Reportable item : lista) {
                writer.println(item.obtenerDatosFila());
            }
            System.out.println("Reporte guardado con éxito en: " + NOMBRE_ARCHIVO);
        } catch (Exception e) {
            System.out.println("Error al guardar el reporte: " + e.getMessage());
        }
    }
}
