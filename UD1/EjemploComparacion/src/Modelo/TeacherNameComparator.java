package Modelo;

import java.util.Comparator;

public class TeacherNameComparator implements Comparator<Teacher> {

    @Override
    public int compare(Teacher o1, Teacher o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
