import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ej5 {
    public static void main(String[] args) {

        String text = "El SOL brilla sobre las MONTAÑAS mientras el río susurra entre las PIEDRAS. A lo lejos, un grupo de " +
                "NIÑOS juega con una pelota roja, riendo y corriendo sin preocupación. " +
                "El viento SUAVE mueve las hojas de los árboles, creando una MELODÍA natural que acompaña la tarde.";

        String regex = "\\b([A-Z][a-z]*|[A-Z]+)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }
}
