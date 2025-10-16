package org.iesch.ad.primerSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class PrimerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimerSpringApplication.class, args);
	}
}