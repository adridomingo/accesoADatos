import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ej3 {
    public static void main(String[] args) {
        //DD/MM/YYYY
        String regex = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})\\b";
        Pattern p = Pattern.compile(regex);
        String fecha = "12/12/2000";

        Matcher m = p.matcher(fecha);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
