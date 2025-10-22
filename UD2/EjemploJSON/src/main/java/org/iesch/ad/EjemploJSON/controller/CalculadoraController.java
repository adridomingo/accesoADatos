package org.iesch.ad.EjemploJSON.controller;

import org.iesch.ad.EjemploJSON.modelo.Numeros;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {
    @PostMapping("/suma")
    public String suma(@RequestBody Numeros numeros) {
        return "El resultado es: " + (numeros.getOperando1() + numeros.getOperando2());
    }
    @PostMapping("/resta")
    public String resta(@RequestBody Numeros numeros) {
        return "El resultado es: " + (numeros.getOperando1() - numeros.getOperando2());
    }
    @PostMapping("/multiplicacion")
    public String multiplicacion(@RequestBody Numeros numeros) {
        return "El resultado es: " + (numeros.getOperando1() * numeros.getOperando2());
    }
    @PostMapping("/division")
    public String division(@RequestBody Numeros numeros) {
        return "El resultado es: " + (numeros.getOperando1() / numeros.getOperando2());
    }
}
