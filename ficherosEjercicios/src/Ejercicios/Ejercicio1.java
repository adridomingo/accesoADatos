package Ejercicios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        String ruta = "src/ej1.txt";
        int contador = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));


            while (br.readLine() != null) {
                contador++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tiene " + contador + " lineas");
    }
}
