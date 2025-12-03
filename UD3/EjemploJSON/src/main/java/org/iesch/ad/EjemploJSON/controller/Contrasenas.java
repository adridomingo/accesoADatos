package org.iesch.ad.EjemploJSON.controller;

import org.iesch.ad.EjemploJSON.modelo.DatosPasswd;
import org.iesch.ad.EjemploJSON.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contrasenas {

    @Autowired
    Utils utils;

    @PostMapping("/generaAZ")
    public String letra(@RequestBody DatosPasswd datos) {
        return utils.generarContrasenasAZ(datos.getLongitud());
    }
    @PostMapping("/generaAlfa")
    public String alfaNumerica(@RequestBody DatosPasswd datos) {
        return utils.generarContrasenasAlfanumericas(datos.getLongitud());
    }

}