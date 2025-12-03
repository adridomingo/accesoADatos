package org.iesch.ad.Ejercicio1.service;

import org.iesch.ad.Ejercicio1.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ProductosService {

    @Autowired
    Map<Long, Product> products;

    public Product getOne(Long id) {
        Product product = products.get(id);
        return product;
    }

    public void addOne(Product product) {
        Long maxKey = Collections.max(products.keySet());
        product.setId(maxKey+1);
        products.put(maxKey+1, product);
    }


    public Product updateOne(Long id, Product product) {
        Product productTmp = products.get(id);

        if (productTmp == null) {
            return null;
        } else {
            product.setId(id);
            products.put(id, product);
            return product;
        }
    }


    public Product deleteOne(Long id) {
        Product productTmp = products.get(id);

        if (productTmp == null) {
            return null;
        } else {
            products.remove(id);
            return productTmp;
        }
    }
}
