package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

@ComponentScan(basePackages = { "controllers" })

public class Application {

	@RequestMapping("/test")
	public String home() {
		return "Hello Docker World Spyre & Mo Awesome app";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
