package Propiedades;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader reader;
        try {
            reader = new FileReader("src/Propiedades/config.properties");

            Properties properties = new Properties();
            properties.load(reader);
            String nombre = properties.getProperty("nombre");
            System.out.println(nombre);
            String puerto = properties.getProperty("puerto");
            System.out.println(puerto);

            System.out.println("------------------------------");
            properties.setProperty("nota", "Notable");
            properties.store(new BufferedWriter(new FileWriter("src/Propiedades/config.properties")), "Ejemplo properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
