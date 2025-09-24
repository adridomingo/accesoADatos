import Modelo.Fruta;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {
    public static void main(String[] args) {
        List<Fruta> frutas = List.of(
                new Fruta("Manzana", "Roja"),
                new Fruta("Pera", "Verde"),
                new Fruta("Limon", "Amarillo"),
                new Fruta("Sandia", "Roja")
        );

        List<String> lista = new ArrayList<>();
        frutas.stream().map(Fruta::getColor).forEach(lista::add);
        System.out.println(lista);
    }
}
