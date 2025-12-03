package org.iesch.ad.primerSpring.controlador;

import org.iesch.ad.primerSpring.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculadora {

    @GetMapping("/sumar/{numero1}/{numero2}")
    public int sumar(@PathVariable int numero1,@PathVariable int numero2) {
        return numero1 + numero2;
    }

    @GetMapping("/restar/{numero1}/{numero2}")
    public int restar(@PathVariable int numero1,@PathVariable int numero2) {
        return numero1 - numero2;
    }

    @GetMapping("/multiplicar/{numero1}/{numero2}")
    public int multiplicar(@PathVariable int numero1,@PathVariable int numero2) {
        return numero1 * numero2;
    }

    @GetMapping("/dividir/{numero1}/{numero2}")
    public int dividir(@PathVariable int numero1,@PathVariable int numero2) {
        return numero1 / numero2;
    }
}
