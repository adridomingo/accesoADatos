import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ej2 {
    public static void main(String[] args) {
        String regex = "^(\\(\\d{3}\\)\\s(\\d{3})\\-(\\d{4}))\\b$";
        //(XXX) XXX-XXXX
        String numeros = "(123) 456-7890";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(numeros);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
