package br.com.so.ServiceOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ServiceOrderApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApplication.class, args);
	}

}
