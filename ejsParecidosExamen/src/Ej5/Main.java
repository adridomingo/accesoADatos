package Ej5;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String text ="&quot;este es un ejemplo de texto para contar palabras";

        Map<String, Long> wordCount = Arrays.stream(text.split("\\s+"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(wordCount);
    }
}
