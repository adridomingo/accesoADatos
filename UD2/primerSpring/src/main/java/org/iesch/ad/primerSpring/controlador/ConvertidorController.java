package org.iesch.ad.primerSpring.controlador;

import jdk.jshell.execution.Util;
import org.iesch.ad.primerSpring.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertidorController {

    @Autowired
    Utils utils;

    @GetMapping("/conDistancia/{km}")
    public Double convertidorDistancias(@PathVariable int km) {
        Double millas = 0d;
        millas = utils.convierteAmillas(km);
        return millas;
    }

    @GetMapping("/conTemp/{temperatura}")
    public Double conTemp (@PathVariable float temperatura) {
        float farenheit = 0f;
        farenheit = utils.conTemperaturas(temperatura);

        return (double) farenheit;
    }
}
