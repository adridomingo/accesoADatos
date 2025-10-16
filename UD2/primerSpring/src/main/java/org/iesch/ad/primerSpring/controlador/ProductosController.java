package org.iesch.ad.primerSpring.controlador;

import org.iesch.ad.primerSpring.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductosController {

    @Autowired
    private Environment env;

    @Value("${name}")
    String name;

    @PostMapping("/producto")
    public void recibeProducto(@RequestBody Producto producto) {
        System.out.println(producto);
        System.out.println(name);
    }

    @GetMapping("/producto")
    public Producto dameProducto() {

        Producto p = Producto.builder().nombre(name)
                .id(10)
                .build();
        return p;
    }
}
