package Ejercicios;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) {

        Path ruta1 = Path.of("src/ej1.txt");
        Path ruta2 = Path.of("src/ej2.txt");

        String ruta3 = "src/ej3.txt";

        List<Path> paths = List.of(ruta1, ruta2);

        for (Path path : paths) {
            try (BufferedReader br1 = new BufferedReader(new FileReader(path.toString()));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(ruta3, true))
            ){
                String line = null;
                while ((line = br1.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
