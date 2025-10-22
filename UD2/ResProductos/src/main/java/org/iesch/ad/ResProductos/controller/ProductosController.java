package org.iesch.ad.ResProductos.controller;

import org.iesch.ad.ResProductos.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductosController {

    @Autowired
    Map<Long,Product> products;

    @GetMapping("/producto")
    public ResponseEntity<?> getTodos() {
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/productoOne")
    public ResponseEntity<?> getOne() {
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }
}
