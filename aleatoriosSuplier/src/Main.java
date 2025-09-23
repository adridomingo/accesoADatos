import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> new Random().nextInt(10);

        for (int i = 1; i <= 10; i++) {
            System.out.println(supplier.get());
        }
    }
}