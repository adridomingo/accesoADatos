package Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio1 {
    public static void main(String[] args) {
        String ruta = "src/ej1.txt";
        int lineCount = countLines(ruta);
        System.out.println("Numero de lineas: " + lineCount);
    }

    private static int countLines(String ruta) {
        int lines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            while (br.readLine() != null) {
                lines++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
