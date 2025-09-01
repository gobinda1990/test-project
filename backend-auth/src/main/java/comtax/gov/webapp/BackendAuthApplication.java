package comtax.gov.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "comtax.gov")
public class BackendAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAuthApplication.class, args);
	}

}
