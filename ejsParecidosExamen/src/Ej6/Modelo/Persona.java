package Ej6.Modelo;

import java.util.List;

public class Persona {
    String nombre;
    String DNI;
    List<Coche> listaCoches;

    public Persona() {
    }

    public Persona(String nombre, String DNI, List<Coche> listaCoches) {
        if (listaCoches.isEmpty()) {

        }
        this.nombre = nombre;
        this.DNI = DNI;
        this.listaCoches = listaCoches;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public List<Coche> getListaCoches() {
        return listaCoches;
    }

    public void setListaCoches(List<Coche> listaCoches) {
        this.listaCoches = listaCoches;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", listaCoches=" + listaCoches +
                '}';
    }
}
