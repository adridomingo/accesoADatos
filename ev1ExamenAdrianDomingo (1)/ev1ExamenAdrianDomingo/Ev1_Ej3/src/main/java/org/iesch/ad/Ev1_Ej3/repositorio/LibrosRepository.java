package org.iesch.ad.Ev1_Ej3.repositorio;

import org.iesch.ad.Ev1_Ej3.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {

//    @NativeQuery(value = "select * from libro l where l.editorial_id in(SELECT id from editorial e where e.nombre = ?1)")
//    List<Libro> findByEditorial_Id(String nombre);

    List<Libro> findByEditorialNombre(String editorial);
}
