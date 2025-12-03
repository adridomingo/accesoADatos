package org.iesch.ad.DemoJPACoches.repositorio;

import org.iesch.ad.DemoJPACoches.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {


}
