package Ejercicios;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Ejercicio2 {
    public static void main(String[] args) {
        Path ruta1 = Path.of("src/ej1.txt");
        Path ruta2 = Path.of("src/ej2.txt");

        try {
            Files.copy(ruta1, ruta2,  StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
