import Modelo.Estudiante;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // LISTAS
        // Ej1
        List<String> lista = List.of("Pepe", "Jose", "Joan");
        lista.forEach(System.out::println);

        //Ej2
        List<Integer> lista2 = List.of(1, 2, 3, 4, 5);
        System.out.println(cogerPares(lista2));

        //Ej3
        List<String> lista3 = List.of("Pepe", "Jose", "Joan", "Adrian");
        System.out.println("La longitud de la palabra mas larga es " + masLargo(lista3));

        // SETS
        //Ej1
        Set<String> set = new HashSet<>();
        set.add("Pepe");
        set.add("Jose");
        set.add("Joan");
        set.add("Adrian");
        set.forEach(System.out::println);

        //Ej2
        List<Integer> lista4 = List.of(1, 1, 2, 3, 4, 4);
        System.out.println(cogerUnicos(lista4));

        //Ej3
        List<String> lista5 = List.of("pera", "pera", "manzana", "naranja");
        System.out.println(cogerUnicosStrings(lista5));

        // Maps
        //Ej1
        Map<String, String> map = new HashMap<>();
        map.put("España", "Madrid");
        map.put("Argentina", "Buenos Aires");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        //Ej2
        System.out.println(contarValores(lista5));

        //Ej3
        List<Estudiante> estudiantes = List.of(
                new Estudiante("Jose", 15, "mecanica"),
                new Estudiante("Adrian", 25, "aeronautica"),
                new Estudiante("Erty", 20, "informatica")
        );
        Map<String, Estudiante> estudiantesMap = new HashMap<>(cogerDatos(estudiantes));
        for (Map.Entry<String, Estudiante> entry : estudiantesMap.entrySet()){
            System.out.println(entry.getKey() + " - " + "Datos: " + entry.getValue());
        }
    }

    // Coge pares
    public static List<Integer> cogerPares(List<Integer> lista) {
        List<Integer> pares = new ArrayList<>();
        for (Integer n : lista) {
            if (n % 2 == 0) {
                pares.add(n);
            }
        }
        return pares;
    };

    // Devuelve longitud del string mas largo
    public static Integer masLargo(List<String> lista) {
        Integer longitud = 0;
        for (String palabra : lista) {
            if (palabra.length() > longitud) {
                longitud = palabra.length();
            }
        }
        return longitud;
    };

    // Devuelve unicos
    public static Set<Integer> cogerUnicos(List<Integer> lista) {
        return new HashSet<>(lista);
    }

    // Devuelve unicos strings
    public static Set<String> cogerUnicosStrings(List<String> lista) {
        return new HashSet<>(lista);
    }

    // Cuenta  valores
    public static Map<String, Integer> contarValores(List<String> lista) {
        Map<String, Integer> map = new HashMap<>();
        for (String palabra : lista) {
            if (!map.containsKey(palabra)) {
                map.put(palabra, 0);
            }
            map.put(palabra, map.getOrDefault(palabra, 0) + 1);
        }
        return map;
    }

    // Estudiantes
    public static Map<String, Estudiante> cogerDatos(List<Estudiante> lista) {
        Map<String, Estudiante> map = new HashMap<>();
        for (Estudiante estudiante : lista) {
            map.put(estudiante.getNombre(), estudiante);
        }
        return map;
    }
}