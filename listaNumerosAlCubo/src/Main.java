import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Integer> listaNumeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Function<Integer, Integer> funcNumeros = (n) -> n * n;

        listaNumeros.stream().map(funcNumeros).forEach(System.out::println);
    }
}