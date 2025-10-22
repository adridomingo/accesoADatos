package org.iesch.ad.ResProductos;

import org.iesch.ad.ResProductos.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ResProductosApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(ResProductosApplication.class, args);
	}

	/*@Autowired
	List<Product> products;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(products);
	}*/

}
