package org.iesch.ad.DemoJPACoches.repositorio;

import org.iesch.ad.DemoJPACoches.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Long> {

    @Query("select c from Coche c where c.color = :color")
    List<Coche> buscaPorColor(@Param("color") String color);

//    @NativeQuery("Select * from Coche c where c.color = :color")
//    List<Coche> buscaPorColor(@Param("color") String color);

    List<Coche> findByColor(String color);
    List<Coche> findByColorAndMarca(String color, String marca);

    List<Coche> findByColorAndMarcaAndPotenciaLessThan(String color, String marca, int potencia);
}
