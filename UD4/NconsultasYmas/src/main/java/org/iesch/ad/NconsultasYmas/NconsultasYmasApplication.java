package org.iesch.ad.NconsultasYmas;

import org.iesch.ad.NconsultasYmas.servicio.CriteriaDemoService;
import org.iesch.ad.NconsultasYmas.servicio.JdbcService;
import org.iesch.ad.NconsultasYmas.servicio.Nmas1DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NconsultasYmasApplication implements CommandLineRunner {

	@Autowired
	Nmas1DemoService nmas1DemoService;

	@Autowired
	CriteriaDemoService criteriaDemoService;

	@Autowired
	JdbcService jdbcTemplateServiceDemoService;

	public static void main(String[] args) {
		SpringApplication.run(NconsultasYmasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n" + "%".repeat(10));
		System.out.println("n + 1");
		System.out.println("\n" + "%".repeat(10));

		nmas1DemoService.mostrarProblemaNmas1();

		System.out.println("\n\n\n" + "%".repeat(20));
		System.out.println("Solucion n + 1");
		System.out.println("\n" + "%".repeat(20));

		nmas1DemoService.solucionFetchJoin();

		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaSimple();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaConWhere();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaConLike();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaConJoin();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaMultiplesCondiciones();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaDinamica("Argentina", 12.0, 1960);
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaOrderBy();
		System.out.println("\n\n" + "-----------".repeat(20));

		criteriaDemoService.ejemploConsultaAgregaciones();
		System.out.println("\n\n" + "-----------".repeat(20));

		System.out.println("\n\t DEMO JDBC TEMPLATE");
		jdbcTemplateServiceDemoService.demoConsultasJDBCTemplate();

	}

}
