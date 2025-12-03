package org.iesch.ad.Ejercicio1.controlador;

import org.iesch.ad.Ejercicio1.modelo.Product;
import org.iesch.ad.Ejercicio1.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ProductoController {

    @Autowired
    ProductosService productosService;

    @Autowired
    Map<Long, Product> products;

    @RequestMapping("/productos")
    public ResponseEntity<?> getTodos() {
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @RequestMapping("/productos/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Long id) {
        Product product = productosService.getOne(id);

        if (product == null) {
            return ResponseEntity.noContent().build();
        } else  {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("/productos")
    public ResponseEntity<?> addProducto(@RequestBody Product product) {

        productosService.addOne(product);
        URI location = URI.create("/producto/" + product.getId());
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Product product) {
        Product productTemp = productosService.updateOne(id, product);

        if (productTemp == null) {
            return ResponseEntity.noContent().build();
        } else  {
            return ResponseEntity.ok(productTemp);
        }
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {

        Product productTemp = productosService.deleteOne(id);

        if (productTemp == null) {
            return ResponseEntity.noContent().build();
        } else   {
            return ResponseEntity.notFound().build();
        }
    }

}
