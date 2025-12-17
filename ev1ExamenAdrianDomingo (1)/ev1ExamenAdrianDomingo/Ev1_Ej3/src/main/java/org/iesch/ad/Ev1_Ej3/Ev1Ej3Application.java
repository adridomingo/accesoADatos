package org.iesch.ad.Ev1_Ej3;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Transactional
public class Ev1Ej3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ev1Ej3Application.class, args);
	}

}
