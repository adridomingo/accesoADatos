package org.iesch.ad.primerSpring.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MiPrimerController {

    @GetMapping("/saludo")
    public String saludo() {
        return "hola mundo";
    }

    @GetMapping("/saluda/{nombre}")
    public Map<String, String> saludarA(@PathVariable String nombre) {
        Map<String,String>map = Map.of("Saludo" , "Hola " + nombre);
        return map;
    }

    @GetMapping("/buscar")
    public Map<String, String> buscar(@RequestParam("nombre") String nombre) {
        Map<String,String>map = Map.of("Saludo" , "Buscamos a " + nombre);
        return map;
    }

}
