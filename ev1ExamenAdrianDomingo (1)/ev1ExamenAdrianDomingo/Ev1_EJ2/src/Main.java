import modelo.Empleado;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Empleado> employees = Arrays.asList(
                new Empleado("Alice", new ArrayList<>(Arrays.asList("Project1", "Project2")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 60000),
                new Empleado("Bob", new ArrayList<>(Arrays.asList("Project3")), new ArrayList<>(Arrays.asList("JavaScript", "React")), 28, 45000),
                new Empleado("Charlie", new ArrayList<>(Arrays.asList("Project1", "Project4")), new ArrayList<>(Arrays.asList("Java", "Angular")), 40, 70000),
                new Empleado("David", new ArrayList<>(Arrays.asList("Project2", "Project3", "Project5")), new ArrayList<>(Arrays.asList("Python", "Django")), 32, 80000),
                new Empleado("Eve", new ArrayList<>(Arrays.asList("Project5")), new ArrayList<>(Arrays.asList("Java", "Spring", "Hibernate")), 25, 50000),
                new Empleado("Frank", new ArrayList<>(Arrays.asList("Project1", "Project3")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 90000),
                new Empleado("Grace", new ArrayList<>(Arrays.asList("Project2", "Project4")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 80000),
                new Empleado("Heidi", new ArrayList<>(Arrays.asList("Project1", "Project3")), new ArrayList<>(Arrays.asList("Java", "Spring")), 35, 38000)
        );


        //1 - Muestra los empleados mayores de 30 años que tengan un salario superior a 50.000.
        System.out.println("Empleados filtrados:");
        employees.stream().filter(e -> e.getEdad() > 30 && e.getSalario()>50000).forEach(System.out::println);
        System.out.println(" - ".repeat(10));

        //2 - Muestra los empleados agrupados a los empleados según el número de proyectos en los que participan.
        System.out.println("Empleados agrupados: ");
        Map<Object, List<Empleado>> map = employees.stream().collect(Collectors.groupingBy(empleado -> empleado.getProyectos().stream().count()));
        map.forEach((o, empleados) -> {
            System.out.println(o);
            System.out.println(empleados);
        });
        System.out.println(" - ".repeat(10));

        //3 - Indica el número de empleados que poseen cada habilidad.
        System.out.println("contador de habilidades:");

        System.out.println(" - ".repeat(10));

        //4 - Ordenar a los empleados por su salario en orden descendente.
        System.out.println("Ordenador por salario: ");
        employees.stream().sorted(Comparator.comparing(Empleado::getSalario).reversed()).forEach(System.out::println);
        System.out.println(" - ".repeat(10));

        //5 - Encuentra al empleado con el salario más alto.
        System.out.println("Salario mas alto: ");
        employees.stream().sorted(Comparator.comparing(Empleado::getSalario).reversed()).limit(1).forEach(System.out::println);
        System.out.println(" - ".repeat(10));

        //6 - Encuentra al empleado con el salario más bajo que tenga más de 30 años.
        System.out.println("Empleado mayor de 30 con el salario mas bajo: ");
        employees.stream().filter(empleado -> empleado.getEdad()>30).sorted(Comparator.comparing(Empleado::getSalario)).limit(1).forEach(System.out::println);
        System.out.println(" - ".repeat(10));

        //7 - Encuentra al empleado con el salario más alto que tenga menos de 30 años y que participe en más de un proyecto.
        List<Empleado> lista = employees.stream().filter(empleado -> empleado.getEdad()<30 && empleado.getProyectos().stream().count() > 1)
                .sorted(Comparator.comparing(Empleado::getSalario).reversed()).limit(1).toList();
        System.out.println("Empleado menor de 30 años con mas de un proyecto y salario mas alto: " + lista);


    }

}