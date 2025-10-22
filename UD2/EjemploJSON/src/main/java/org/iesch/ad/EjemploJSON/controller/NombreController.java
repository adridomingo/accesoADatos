package org.iesch.ad.EjemploJSON.controller;

import org.iesch.ad.EjemploJSON.modelo.Persona;
import org.iesch.ad.EjemploJSON.services.GuardaDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NombreController {

    @Autowired
    GuardaDatos guardaDatos;

    // Recibe JSON y guarda en un fichero
    @PostMapping("/persona")
    public String guarda(@RequestBody Persona persona) {
        System.out.println(persona);
        return guardaDatos.guarda(persona);
    }
}
