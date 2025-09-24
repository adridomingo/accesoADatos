import Modelo.Fruta;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ejercicio1 {
    public static void main(String[] args) {
        List<Fruta> frutas = List.of(
                new Fruta("Manzana", "Roja"),
                new Fruta("Pera", "Verde"),
                new Fruta("Limon", "Amarillo"),
                new Fruta("Sandia", "Roja")
                );

        List<String> lista = new ArrayList<>();
        frutas.stream().map(Fruta::getNombre).forEach(lista::add);
        System.out.println(lista);
    }
}