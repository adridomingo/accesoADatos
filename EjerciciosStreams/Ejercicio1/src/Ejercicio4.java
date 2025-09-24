import Modelo.Persona;

import java.util.Comparator;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) {
        List<Persona> personas = List.of(
                new Persona("Pepe", 18),
                new Persona("Jorge", 20),
                new Persona("Adrian", 15)
        );
        Comparator<Persona> comparator = Comparator.comparing(Persona::getEdad).reversed();
        personas.stream().sorted(comparator).forEach(System.out::println);

        personas.stream().sorted((p1, p2) -> p2.getEdad() - p1.getEdad())
                .forEach(System.out::println);

        // Varias formas
        Comparator<Persona> comparator1 = Comparator.comparing(Persona::getNombre);
        personas.stream().sorted(comparator1).forEach(System.out::println);

        personas.stream().sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()))
                .forEach(System.out::println);

        personas.stream().sorted(Comparator.comparing(Persona::getNombre))
                .forEach(System.out::println);
    }
}
