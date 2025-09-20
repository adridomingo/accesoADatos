import Modelo.Calculator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculator suma = (a, b) -> a+b;
        Calculator resta = (a, b) -> a-b;
        Calculator multiplicacion = (a, b) -> a*b;
        Calculator division = (a, b) -> {
            if (b == 0 || a == 0) return 0;
            return a/b;
        };

        System.out.println(suma.calculate(2, 3) + "\n" +
                resta.calculate(5, 2) + "\n" +
                multiplicacion.calculate(5, 5) + "\n" +
                division.calculate(5, 5)
        );
    }
}