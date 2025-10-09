import Modelo.Student;
import Modelo.Teacher;
import Modelo.TeacherNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Student student = new Student(4, "Juan");
        Student student1 = new Student(1, "Antonio");
        Student student2 = new Student(3, "Lucas");
        Student student3 = new Student(2, "Mateo");

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        Collections.sort(students);

        System.out.println(students);
        */
        Teacher profe = new Teacher(61, "pepe");
        Teacher profe1 = new Teacher(2, "Juan");
        Teacher profe2 = new Teacher(3, "Antonio");
        Teacher profe3 = new Teacher(4, "dimitri");
        Teacher profe4 = new Teacher(5, "Joselu");

        List<Teacher> teachers = new ArrayList<>(List.of(profe, profe1, profe2, profe3, profe4));
        Collections.sort(teachers, new TeacherNameComparator());
        System.out.println(teachers);



    }
}