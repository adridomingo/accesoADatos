package Ejercicios;

import Modelo.Producto;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio5 {
    public static void main(String[] args) {
        // Crear 5 productos y meterlos en un ArrayList
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Laptop", 899.99, true, 'E'));
        productos.add(new Producto(2, "Ratón", 25.50, false, 'A'));
        productos.add(new Producto(3, "Teclado", 75.00, true, 'A'));
        productos.add(new Producto(4, "Monitor", 299.99, false, 'E'));
        productos.add(new Producto(5, "Auriculares", 49.99, true, 'A'));

        String nombreFichero = "productos.dat";

        // Tamaño de cada registro:
        // int id = 4 bytes
        // String nombre = 40 bytes (20 caracteres * 2 bytes cada uno)
        // double precio = 8 bytes
        // boolean descuento = 1 byte
        // char tipo = 2 bytes
        // TOTAL = 55 bytes por producto
        int tamañoRegistro = 55;

        // ESCRIBIR productos en el fichero de acceso aleatorio
        System.out.println("=== ESCRIBIENDO PRODUCTOS EN EL FICHERO ===\n");
        try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "rw")) {
            for (Producto p : productos) {
                // Escribir id
                raf.writeInt(p.getId());

                // Escribir nombre (ajustado a 20 caracteres)
                StringBuilder nombreAjustado = new StringBuilder(p.getNombre());
                nombreAjustado.setLength(20);
                raf.writeChars(nombreAjustado.toString());

                // Escribir precio
                raf.writeDouble(p.getPrecio());

                // Escribir descuento
                raf.writeBoolean(p.isDescuento());

                // Escribir tipo
                raf.writeChar(p.getTipo());

                System.out.println("Escrito: " + p);
            }
            System.out.println("\n✓ Productos guardados correctamente\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }

        // LEER y mostrar TODO el contenido del fichero
        System.out.println("=== LEYENDO TODO EL CONTENIDO DEL FICHERO ===\n");
        try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "r")) {
            long numRegistros = raf.length() / tamañoRegistro;

            for (int i = 0; i < numRegistros; i++) {
                raf.seek(i * tamañoRegistro);

                // Leer id
                int id = raf.readInt();

                // Leer nombre
                char[] nombreChars = new char[20];
                for (int j = 0; j < 20; j++) {
                    nombreChars[j] = raf.readChar();
                }
                String nombre = new String(nombreChars).trim();

                // Leer precio
                double precio = raf.readDouble();

                // Leer descuento
                boolean descuento = raf.readBoolean();

                // Leer tipo
                char tipo = raf.readChar();

                System.out.println("Producto " + (i + 1) + ": id=" + id +
                        ", nombre='" + nombre + "'" +
                        ", precio=" + precio +
                        ", descuento=" + descuento +
                        ", tipo=" + tipo);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }

        // LEER y mostrar el CUARTO producto
        System.out.println("=== LEYENDO EL CUARTO PRODUCTO ===\n");
        try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "r")) {
            int posicionCuarto = 3; // El cuarto producto está en la posición 3 (0-indexed)
            raf.seek(posicionCuarto * tamañoRegistro);

            // Leer id
            int id = raf.readInt();

            // Leer nombre
            char[] nombreChars = new char[20];
            for (int j = 0; j < 20; j++) {
                nombreChars[j] = raf.readChar();
            }
            String nombre = new String(nombreChars).trim();

            // Leer precio
            double precio = raf.readDouble();

            // Leer descuento
            boolean descuento = raf.readBoolean();

            // Leer tipo
            char tipo = raf.readChar();

            System.out.println("Cuarto producto: id=" + id +
                    ", nombre='" + nombre + "'" +
                    ", precio=" + precio +
                    ", descuento=" + descuento +
                    ", tipo=" + tipo);
        } catch (IOException e) {
            System.err.println("Error al leer el cuarto producto: " + e.getMessage());
        }
    }
}