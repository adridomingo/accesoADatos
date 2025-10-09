package Ej8;

import Ej8.Modelo.Cancion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Cancion> canciones = new ArrayList (
                Arrays.asList(
                        new Cancion("Livin on Prayer", "Bon Jovi"),
        new Cancion("Long Hot Summer", "Keith Urban"),
        new Cancion("Its my Life", "Bon Jovi"),
        new Cancion("Dolor Fantasma", "Amadeus"),
        new Cancion("Run To You", "Bryan Adams"),
        new Cancion("Summer of 69", "Bryan Adams"),
        new Cancion("Paranoid", "Black Sabbath"),
        new Cancion("Cherokee", "Europe"),
        new Cancion("River Bank", "Brad Paisley")
));
        filtradoConBucles(canciones);
        filtradoFuncional(canciones, "Bon Jovi");
        filtradoFuncionalAnuevaLista(canciones, "Bon Jovi");
        numeroCancionesDeCantante(canciones, "Bon Jovi");
        agruparCancionesPorCantante(canciones);
        sinDuplicados(canciones);
    }




    private static void filtradoConBucles(List<Cancion> canciones) {
        List<Cancion> listaFiltrada = new LinkedList<>();
        for (Cancion cancion: canciones) {
            if (cancion.getCantante().equals("Bon Jovi")) {
                listaFiltrada.add(cancion);
            }
        }
        for (Cancion c : listaFiltrada) {
            System.out.println(c.toString());
        }
    }

    private static void filtradoFuncional(List<Cancion> canciones, String cantante) {
        System.out.println("Funcional");
        canciones.stream().filter(c -> c.getCantante().equals(cantante))
                .forEach(System.out::println);
    }

    private static void filtradoFuncionalAnuevaLista(List<Cancion> canciones, String cantante) {
        System.out.println("Funcional acaban en nueva lista: ");
        List<Cancion> listaCanciones =canciones.stream().filter(c -> c.getCantante().equals(cantante))
                .toList();

        for (Cancion c : canciones) {
            System.out.println(c.toString());
        }
    }

    private static void numeroCancionesDeCantante(List<Cancion> canciones, String cantante) {
        long numeroCantantes = canciones.stream().filter(cancion -> cancion.getCantante().equals(cantante))
                .count();
        System.out.println("El cantante: " + cantante + " tiene " + numeroCantantes + " canciones");
    }

    private static void agruparCancionesPorCantante(List<Cancion> canciones) {
        canciones.stream().collect(Collectors.groupingBy(Cancion::getCantante, Collectors.counting()))
                .forEach((cantante, numero) -> System.out.println("Cantante: " + cantante + " tiene " + numero + " canciones"));
    }

    private static void sinDuplicados(List<Cancion> canciones) {
        canciones.stream().distinct().forEach(System.out::println);
    }
}
