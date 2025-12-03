package org.iesch.ad.demoConsume;

import org.iesch.ad.demoConsume.modelo.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoConsumeApplication  {
	//implements CommandLineRunner
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoConsumeApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		String url = "https://catfact.ninja/fact";
//		restTemplate.getForObject(url, Fact.class);
//
////		for (int i = 0; i < 20; i++) {
////			Fact miFact = restTemplate.getForObject(url, Fact.class);
////			System.out.println(miFact);
////		}
//
//	}
}
