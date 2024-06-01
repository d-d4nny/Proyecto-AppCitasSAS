package appCitas.AppCitasSASv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppCitasSasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppCitasSasApplication.class, args);
	}

}
