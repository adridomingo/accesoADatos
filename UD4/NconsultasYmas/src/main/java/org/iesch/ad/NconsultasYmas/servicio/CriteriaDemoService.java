package org.iesch.ad.NconsultasYmas.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
