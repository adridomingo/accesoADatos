package org.iesch.ad.ResProductos.controller;

import org.iesch.ad.ResProductos.modelo.Product;
import org.iesch.ad.ResProductos.services.ProductosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
public class ProductosController {

    private static final Logger logger = LoggerFactory.getLogger(ProductosController.class);

    @Autowired
    ProductosService productosService;

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

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        logger.debug("Buscando el producto con ID: {}", id);
        Product product = productosService.getOne(id);
        if (product == null) {
            logger.warn("Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("/producto")
    public ResponseEntity<?> addOne(@RequestBody Product product) {
        logger.info("Creando nuevo producto: {}", product);
        productosService.addOne(product);
        //return ResponseEntity.status(HttpStatus.CREATED).body(product);
        URI location = URI.create("/producto/" + product.getId());
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> updateOne(@RequestBody Product product, @PathVariable Long id) {
        logger.info("Modificando producto con ID: {}", id);
        logger.debug("Datos: {}",product);
        Product productTmp = productosService.updateOne(product, id);
        if (productTmp == null) {
            logger.warn("Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(productTmp);
        }
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        logger.info("Eliminando producto con ID: {}", id);
        Product productTmp = productosService.deleteOne(id);
        if (productTmp == null) {
            logger.warn("Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build(); // 404
        } else {
            return ResponseEntity.noContent().build(); // 204
        }
    }

}
