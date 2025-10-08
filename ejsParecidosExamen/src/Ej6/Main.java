package Ej6;

import Ej6.Modelo.Coche;
import Ej6.Modelo.Persona;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Coche coche = new Coche("Seat", "Rojo", "1234");
        Coche coche2 = new Coche("Ford", "Azul", "1234");
        Coche coche3 = new Coche("Renault", "Verde", "1234");
        Coche coche4 = new Coche("Citroen", "Rojo", "1234");
        Coche coche5 = new Coche("Fiat", "Amarillo", "1234");
        Coche coche6 = new Coche("Opel", "Blanco", "1234");
        Coche coche8 = new Coche("Peugeot", "Azul", "1234");
        Coche coche9 = new Coche("Mercedes", "Verde", "1234");
        Coche coche10 = new Coche("BMW", "Amarillo", "1234");
        Coche coche11 = new Coche("Audi", "Blanco", "1234");
        Coche coche12 = new Coche("Volkswagen", "Rojo", "1234");
        Coche coche13 = new Coche("Toyota", "Azul", "1234");
        Coche coche14 = new Coche("Hyundai", "Verde", "1234");
        Coche coche15 = new Coche("Kia", "Amarillo", "1234");
        Coche coche16 = new Coche("Mazda", "Rojo", "1234");
        Coche coche17 = new Coche("Honda", "Verde", "1234");
        Coche coche18 = new Coche("Suzuki", "Amarillo", "1234");
        Coche coche19 = new Coche("Mitsubishi", "Blanco", "1234");
        Coche coche20 = new Coche("Subaru", "Rojo", "1234");
        Coche coche21 = new Coche("BYD", "Rojo", "1234");
        Coche coche22 = new Coche("Dr", "Azul", "1234");

        Persona persona = new Persona("Juan","18444555A" ,List.of(coche, coche2, coche3));
        Persona persona2 = new Persona("Pedro","18444555A" ,List.of(coche4, coche5, coche6));
        Persona persona3 = new Persona("Maria","18444555A" ,List.of(coche8, coche9, coche10));
        Persona persona4 = new Persona("Antonio","18444555A" ,List.of(coche11, coche12, coche13));
        Persona persona5 = new Persona("Lucia","18444555A" ,List.of(coche14, coche15, coche16));
        Persona persona6 = new Persona("Carlos","18444555A" ,List.of(coche17, coche18, coche19));
        Persona persona7 = new Persona("Ana","18444555A" ,List.of(coche20, coche21, coche22, coche4));

        List<Persona> personas = List.of(persona, persona2, persona3, persona4, persona5, persona6, persona7);

        List<Persona> personasConCocheRojo = personas.stream()
                .filter(persona1 -> persona1.getListaCoches()
                        .stream().anyMatch(coche1 -> "Rojo".equals(coche1.getColor()))).toList();

        System.out.println(personasConCocheRojo);
        System.out.println();
        List<Persona> personasConOpel = personas.stream()
                .filter(persona1 -> persona1.getListaCoches()
                        .stream().anyMatch(coche1 -> coche1.getMarca().equals("Opel"))).toList();

        System.out.println(personasConOpel);
        System.out.println();

        Persona personaConMasCoches = personas.stream()
                .max((p1, p2) -> p1.getListaCoches().size() - p2.getListaCoches().size())
                .orElse(null);

        System.out.println(personaConMasCoches);
    }
}
