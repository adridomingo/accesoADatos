package org.iesch.ad.ResProductos.services;

import org.iesch.ad.ResProductos.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ProductosService {

    @Autowired
    Map<Long, Product> productos;

    public Product getOne(Long id) {
        Product product = productos.get(id);
        return product;
    }

    public void addOne(Product product) {
        Long maxKey = Collections.max(productos.keySet());
        product.setId(maxKey);
        productos.put(maxKey+1, product);
    }

    public Product updateOne(Product product, Long id) {

        Product productTmp = productos.get(id);

        if (productTmp == null) {
            return null;
        } else {
            product.setId(id);
            productos.put(id, product);
            return product;
        }
    }


    public Product deleteOne(Long id) {

        Product productTmp = productos.get(id);

        if (productTmp == null) {
            return null;
        } else {
            productos.remove(id);
            return productTmp;
        }
    }
}
