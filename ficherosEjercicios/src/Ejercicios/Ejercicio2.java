package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

public class Ejercicio2 {
    public static void main(String[] args) {
        Path ruta1 = Path.of("src/ej1.txt");
        Path ruta2 = Path.of("src/ej2.txt");

        copyFile(ruta1, ruta2);
    }

    private static void copyFile(Path ruta1, Path ruta2) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta1.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ruta2.toFile()))){
            String line;
            while ((line=br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
