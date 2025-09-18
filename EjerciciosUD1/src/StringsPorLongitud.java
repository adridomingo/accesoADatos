import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringsPorLongitud {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Perro", "Gato", "Elefante", "Conejo", "Mariposa");
        Collections.sort(list, (string1, string2) -> string1.length() - string2.length());

        System.out.println(list);
    }
}
