package mainPackage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import mainPackage.storage.StorageService;

@SpringBootApplication
@EnableScheduling
public class Main {

	public static void main(String[] args) {
		
		SpringApplication.run(Main.class, args);

	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
