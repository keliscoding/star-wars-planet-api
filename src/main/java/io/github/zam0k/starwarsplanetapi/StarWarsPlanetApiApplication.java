package io.github.zam0k.starwarsplanetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StarWarsPlanetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsPlanetApiApplication.class, args);
	}

}
