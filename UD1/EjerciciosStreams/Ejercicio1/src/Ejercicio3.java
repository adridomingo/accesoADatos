import java.util.List;

public class Ejercicio3 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        System.out.println(list.stream().map(n -> n * n).reduce(0, Integer::sum));
    }
}
