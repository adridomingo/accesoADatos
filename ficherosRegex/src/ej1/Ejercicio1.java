package ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio1 {
    public static void main(String[] args) {
        String a = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern p = Pattern.compile(a);

        try (BufferedReader br = new BufferedReader(new FileReader("src/ej1/ips.txt"))) {
            String line;

            while((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    System.out.println(m.group());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
