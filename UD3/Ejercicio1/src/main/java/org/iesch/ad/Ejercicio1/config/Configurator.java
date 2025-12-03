package org.iesch.ad.Ejercicio1.config;

import org.iesch.ad.Ejercicio1.modelo.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configurator {

    @Bean
    public Map<Long, Product> inicializa() {
        Map<Long, Product> products = new HashMap();
        products.put(1L,Product.builder().id(1L).name("Fairy").category("Limpieza").price(5.25).description("Lavavajillas mano").stock(15).build());
        products.put(2L,Product.builder().id(2L).name("Calgonit").category("Limpieza").price(15.0).description("Lavavajillas mano").stock(25).build());
        products.put(3L,Product.builder().id(3L).name("Chorizo Ciervo").category("Alimentacion").price(8.56).description("Chorizo Ciervo").stock(15).build());
        products.put(4L,Product.builder().id(4L).name("Pan").category("Alimentacion").price(1.20).description("pan").stock(21).build());
        products.put(5L,Product.builder().id(5L).name("Aceite Oliva").category("Alimentacion").price(8.26).description("aceite virgen extra").stock(10).build());

        return products;
    }


}
