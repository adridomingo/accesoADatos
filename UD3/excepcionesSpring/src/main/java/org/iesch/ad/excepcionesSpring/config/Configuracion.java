package org.iesch.ad.excepcionesSpring.config;

import org.iesch.ad.excepcionesSpring.modelo.Estudiante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configuracion {

    @Bean
    public Map<Long, Estudiante> inicializa() {
        Map<Long, Estudiante> estudiantes = new HashMap<>();
        Estudiante estudiante1 = new Estudiante(1L, "Juan", "Garcia Lopez", "juan@iesch.org", 20, "DAM");
        Estudiante estudiante2 = new Estudiante(2L, "Maria", "Rodriguez", "maria@iesch.org", 19, "DAW");
        Estudiante estudiante3 = new Estudiante(3L, "Pedro", "Martinez", "pedro@iesch.org", 22, "ASIR");

        estudiantes.put(estudiante1.getId(), estudiante1);
        estudiantes.put(estudiante2.getId(), estudiante2);
        estudiantes.put(estudiante3.getId(), estudiante3);

        return estudiantes;
    }
}
