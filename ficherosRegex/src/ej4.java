import java.util.regex.Pattern;

public class ej4 {
    public static void main(String[] args) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>?/]).{8,}$";

        Pattern pattern = Pattern.compile(regex);

        String[] passwords = {
                "Password123!",
                "password123!",
                "PASSWORD123!",
                "Password!!!",
                "Pass12!",
                "Secure#2023"
        };

        for (String pwd : passwords) {
            System.out.printf("%-15s -> %s%n", pwd,
                    pattern.matcher(pwd).matches() ? "válida" : "inválida");
        }
    }
}
