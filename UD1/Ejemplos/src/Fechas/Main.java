package Fechas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2022, Calendar.JANUARY, 1);

        LocalDate fecha = LocalDate.of(2022, Month.JANUARY, 1); //Inmtuable
        String texto = "Hola Mundo";

        // Horas Inmutables
        LocalTime hora = LocalTime.of(10, 0);

        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
        LocalDateTime fechaHora2 = LocalDateTime.of(2025, 9, 25, 10, 30);

        LocalDateTime haceUnaSemana = fechaHora.minusDays(300);
        System.out.println(haceUnaSemana);

        String fechaFormato = haceUnaSemana.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(fechaFormato);
    }
}
