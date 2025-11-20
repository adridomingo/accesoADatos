package org.iesch.ad.NconsultasYmas.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriteriaDemoService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void ejemploConsultaSimple() {

        System.out.println("Select * from autores");
        //1 Crear builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //2 Entidad a Obtener
        CriteriaQuery<Autor> criteriaQuery = cb.createQuery(Autor.class);
        //3 Crear root (FROM)
        Root<Autor> autor = criteriaQuery.from(Autor.class);
        //4 Construir el select
        criteriaQuery.select(autor);
        //5 Crear y ejecutar la consulta
        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
        //6 Resultado
        List<Autor> autores = query.getResultList();

        System.out.println("Resultados encontrados " + autores.size());
        for (Autor a : autores) {
            System.out.println(" - " + a.getNombre() + " " + a.getApellido());
        }


    }

    @Transactional
    public void ejemploConsultaConWhere() {
        System.out.println("Select * from autores where nacionalidad = 'Argentina'");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Autor> criteriaQuery = cb.createQuery(Autor.class);
        Root<Autor> autor = criteriaQuery.from(Autor.class);

        // Where
        criteriaQuery.select(autor)
                .where(cb.equal(autor.get("nacionalidad"), "Argentina"));

        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
        List<Autor> autores = query.getResultList();

        System.out.println("Resultados encontrados " + autores.size());
        for (Autor a : autores) {
            System.out.println(" - " + a.getNombre() + " " + a.getApellido());
        }

    }

    public void ejemploConsultaConLike() {
        System.out.println("COnsulta con like");
        System.out.println("Select * from autores where nombre like '%Jorge%'");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Autor> criteriaQuery = cb.createQuery(Autor.class);
        Root<Autor> autor = criteriaQuery.from(Autor.class);

        // Where nombre
        criteriaQuery.select(autor)
                .where(cb.like(autor.get("nombre"), "%Jorge%"));

        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
        List<Autor> autores = query.getResultList();

        for (Autor a : autores) {
            System.out.println(" - " + a.getNombre() + " " + a.getApellido());
        }


    }

    public void ejemploConsultaConJoin() {
        System.out.println("Consulta con Join");
        System.out.println("Select l from libro l join l.autor a where a.nacionalidad = 'Colombiana'");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Libro> criteriaQuery = cb.createQuery(Libro.class);
        Root<Libro> libro = criteriaQuery.from(Libro.class);

        // Join con tabla autor
        Join<Libro, Autor> autorJoin = libro.join("autor");
        // Where
        criteriaQuery.select(libro)
                .where(cb.equal(autorJoin.get("nacionalidad"), "Colombiana"));
        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);
        List<Libro> libros = query.getResultList();

        System.out.println("Libros de colombianos: ");
        for (Libro l : libros) {
            System.out.println(" - " + l.getTitulo() + " " + l.getAnioPublicacion());
        }
    }

    public void ejemploConsultaMultiplesCondiciones() {
        System.out.println("Consulta con MultiplesCondiciones");
        System.out.println("Select * from Libros where precio > 15 and anio_publicacion > 1960");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Libro> criteriaQuery = cb.createQuery(Libro.class);
        Root<Libro> libro = criteriaQuery.from(Libro.class);

        Predicate precioMayorA15 = cb.gt(libro.get("precio"), 15.0);
        Predicate anioDespues1960 = cb.gt(libro.get("anioPublicacion"), 1960);

        criteriaQuery.select(libro)
                .where(cb.and(precioMayorA15, anioDespues1960));

        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);
        List<Libro> libros = query.getResultList();

        System.out.println("Libros mayores a 15€ y 1960");

        for (Libro l : libros) {
            System.out.println(" - " + l.getTitulo() + " " + l.getAnioPublicacion());
        }
    }

    public void ejemploConsultaDinamica(String nacionalidad, Double precioMin, Integer anioMin) {
        System.out.println("Consulta Dinamica");
        System.out.println("Select l from libro l join l.autor a where a.nacionalidad = ? and precio = ? and anioPublicacion = ?");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Libro> criteriaQuery = cb.createQuery(Libro.class);
        Root<Libro> libro = criteriaQuery.from(Libro.class);

        // Join con tabla autor
        Join<Libro, Autor> autorJoin = libro.join("autor");

        // Lista predicados
        List<Predicate> predicates = new ArrayList<>();

        //Añado condiciones
        if (nacionalidad != null && !nacionalidad.isEmpty()) {
            predicates.add(cb.equal(autorJoin.get("nacionalidad"), nacionalidad));
        }

        if (precioMin != null) {
            predicates.add(cb.ge(libro.get("precio"), precioMin));
        }

        if (anioMin != null) {
            predicates.add(cb.ge(libro.get("anioPublicacion"), anioMin));
        }

        // Combinar
        if (!predicates.isEmpty()) {
            criteriaQuery.select(libro)
                    .where(cb.and(predicates.toArray(new Predicate[0])));
        } else {
            criteriaQuery.select(libro);
        }

        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);
        List<Libro> libros = query.getResultList();

        System.out.println("Libros con criterios dinamicos: ");
        for (Libro l : libros) {
            System.out.println(" - " + l.getTitulo() + " " + l.getAnioPublicacion() + " " + l.getPrecio());
        }


    }

    public void ejemploConsultaOrderBy() {
        System.out.println("Consulta con OrderBy");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Libro> criteriaQuery = cb.createQuery(Libro.class);
        Root<Libro> libro = criteriaQuery.from(Libro.class);

        // Order by por año de publicacion
        criteriaQuery.select(libro)
                .orderBy(cb.desc(libro.get("anioPublicacion")));

        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);
        List<Libro> libros = query.getResultList();

        System.out.println("Libros ordenados de manera Desc");

        for (Libro l : libros) {
            System.out.println(" - " + l.getTitulo() + " " + l.getAnioPublicacion());
        }
    }

    public void ejemploConsultaAgregaciones() {
        System.out.println("Consulta con GroupBy");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> countQuery = cb.createQuery(Object[].class);
        Root<Libro> libro = countQuery.from(Libro.class);

        Join<Libro, Autor> autorJoin = libro.join("autor");

        countQuery.multiselect(
                autorJoin.get("nombre"),
                autorJoin.get("apellido"),
                cb.count(libro)
                ).groupBy(
                        autorJoin.get("id"),
                        autorJoin.get("nombre"),
                        autorJoin.get("apellido"));

        TypedQuery<Object[]> query = entityManager.createQuery(countQuery);
        List<Object[]> resultados = query.getResultList();

        System.out.println("Contamos libros");

        for (Object[] resultado : resultados) {
            System.out.println(" - " + resultado[0] + " " + resultado[1] + " " + resultado[2] + ": Libros");
        }

        // Buscar el libro con el precio maximo
        System.out.println("Precio max");

        CriteriaQuery<Double> maxQuery = cb.createQuery(Double.class);
        Root<Libro> libroRoot = maxQuery.from(Libro.class);
        maxQuery.select(cb.max(libroRoot.get("precio")));

        Double precioMax = entityManager.createQuery(maxQuery).getSingleResult();
        System.out.println("\t - Precio maximo: " + precioMax);
    }
}
