import Modelo.Empleado;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio5 {
    public static void main(String[] args) {
        List<Empleado>  empleados = List.of(
                new Empleado("Jose", "Recursos Humanos"),
                new Empleado("Samuel", "Electronica"),
                new Empleado("Rodrigo", "Informatica")
        );

        Comparator<Empleado> departamento = Comparator.comparing(Empleado::getDepartamento);
        empleados.stream().sorted(departamento).forEach(System.out::println);
        System.out.println();

        Map<String, Long> mapa = empleados.stream().
                collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));
        System.out.println(mapa);
        System.out.println();
        String dep = "Electronica";
        empleados.stream().filter(e -> e.getDepartamento().equalsIgnoreCase(dep)).forEach(System.out::println);
        System.out.println();
        String nombreEmp = "Samuel";
        empleados.stream().map(Empleado::getNombre).filter(nombre -> nombre.equalsIgnoreCase(nombreEmp)).forEach(System.out::println);

    }
}
