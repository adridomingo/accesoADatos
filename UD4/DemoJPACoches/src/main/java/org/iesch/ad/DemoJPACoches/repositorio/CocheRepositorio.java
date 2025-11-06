package org.iesch.ad.DemoJPACoches.repositorio;

import org.iesch.ad.DemoJPACoches.modelo.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche, Long> {


}
