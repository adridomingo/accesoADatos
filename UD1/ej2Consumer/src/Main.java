import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Consumer<String> print = System.out::println;
        List<String> lista = List.of("coche", "rueda", "motor", "ventana", "cristal", "manzana");

        lista.forEach(print);
    }
}