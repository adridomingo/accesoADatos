package Ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {
    public static void main(String[] args) {
        // Directorio donde buscar
        String directorio = "."; // Directorio actual
        String extension = ".txt"; // Extensión a buscar
        int profundidadMaxima = 3; // Niveles máximos a escanear

        // Buscar archivos con profundidad máxima
        List<String> archivos = buscarArchivos(new File(directorio), extension, profundidadMaxima);

        // Mostrar resultados
        System.out.println("Archivos encontrados: " + archivos.size());
        System.out.println("Profundidad máxima: " + profundidadMaxima + " niveles\n");
        for (String ruta : archivos) {
            System.out.println(ruta);
        }
    }

    // Método recursivo para buscar archivos con profundidad máxima
    public static List<String> buscarArchivos(File directorio, String extension, int profundidadMaxima) {
        List<String> resultados = new ArrayList<>();

        // Verificar que el directorio existe y que la profundidad es válida
        if (!directorio.exists() || !directorio.isDirectory() || profundidadMaxima < 0) {
            return resultados;
        }

        // Obtener todos los archivos y carpetas
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    // Si es carpeta y aún hay profundidad, buscar recursivamente
                    if (profundidadMaxima > 0) {
                        resultados.addAll(buscarArchivos(archivo, extension, profundidadMaxima - 1));
                    }
                } else if (archivo.getName().endsWith(extension)) {
                    // Si es archivo y coincide la extensión, añadir
                    resultados.add(archivo.getAbsolutePath());
                }
            }
        }

        return resultados;
    }
}
