import java.util.regex.Pattern;

public class ej4 {
    public static void main(String[] args) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>?/]).{8,}$";

        Pattern pattern = Pattern.compile(regex);

        String[] passwords = {
                "Password123!",     // ✅ válida
                "password123!",     // ❌ falta mayúscula
                "PASSWORD123!",     // ❌ falta minúscula
                "Password!!!",      // ❌ falta número
                "Pass12!",          // ❌ menos de 8 caracteres
                "Secure#2023"       // ✅ válida
        };

        for (String pwd : passwords) {
            System.out.printf("%-15s -> %s%n", pwd,
                    pattern.matcher(pwd).matches() ? "válida" : "inválida");
        }
    }
}
