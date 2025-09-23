import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> listaNombres = List.of("Pepe", "Maria", "Jose", "Maria", "Adrian");

        Predicate<String> predicate = n -> n.toUpperCase().startsWith("M");
        listaNombres.stream().filter(predicate).forEach(System.out::println);
    }
}