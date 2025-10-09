package Ej8.Modelo;

import java.util.Objects;

public class Cancion {
    String titulo;
    String cantante;

    public Cancion(String titulo, String cantante) {
        this.titulo = titulo;
        this.cantante = cantante;
    }

    public Cancion() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", cantante='" + cantante + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Cancion cancion = (Cancion) o;
        return Objects.equals(titulo, cancion.titulo) && Objects.equals(cantante, cancion.cantante);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(titulo);
        result = 31 * result + Objects.hashCode(cantante);
        return result;
    }
}
