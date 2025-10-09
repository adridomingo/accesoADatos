package Optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional <Double> resultado = calculaMedia(5d,6d,7d,8d,9d,10d);
        if (resultado.isPresent()) {
            System.out.println(resultado.get());
        } else {
            System.out.println("Nada");
        }
    }

    private static Optional <Double> calculaMedia(Double ...notas) {
        if (notas.length == 0) {
            return Optional.empty();
        } else {
            double sum = 0;
            for(Double nota : notas) sum += nota;
            return Optional.of( sum/notas.length);
        }
    }
}
