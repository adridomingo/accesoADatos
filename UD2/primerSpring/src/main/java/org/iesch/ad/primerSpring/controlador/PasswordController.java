package org.iesch.ad.primerSpring.controlador;

import org.iesch.ad.primerSpring.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    @Autowired
    Utils utils;

    @GetMapping("/generaLetras/{numeroCaracteres}")
    public String Ej1 (@PathVariable int numeroCaracteres) {

        String pass = utils.generarContrasenasAZ(numeroCaracteres);

        return pass;
    }

    @GetMapping("/genera")
    public String Ej1_2() {
        int longitud = 32;
        String pass = utils.generarContrasenasAlfanumericas(longitud);

        return pass;
    }
}
