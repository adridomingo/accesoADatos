package Ej4;

import Ej4.Modelo.Producto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //leer
        List<Producto> listaProductos = leerProductos("src/Ej4/Ej4.csv");

        // Producto mas caro
        listaProductos.stream().collect(Collectors.groupingBy(Producto::getCategoria,
                Collectors.maxBy(Comparator.comparing(Producto::getPrecio))))
                .forEach((categoria, producto) -> {
                    System.out.println("Producto mas caro de la categoria: " + categoria + ": "
                    + producto.orElse(null));
                });

        // Listo precio entre 10 y 20
        List<Producto> listaEntre10Y20 = listaProductos.stream().filter(p -> p.getPrecio() >= 10 && p.getPrecio() <= 20).toList();
        System.out.println("Entre 10 y 20: " + listaEntre10Y20);
    }

    private static List<Producto> leerProductos(String s) {
        List<Producto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            String line;
            line = br.readLine();

            while ((line = br.readLine())!=null) {
                String[] campos = line.split(";");
                String nombre = campos[0];
                Double precio = Double.parseDouble(campos[1].replace(",", "."));
                String categoria = campos[2];
                Producto p = new Producto(nombre, precio, categoria);
                lista.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }


}
