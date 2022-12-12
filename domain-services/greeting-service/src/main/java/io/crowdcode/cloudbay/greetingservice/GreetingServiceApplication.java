package io.crowdcode.cloudbay.greetingservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class GreetingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingServiceApplication.class, args);
	}

}
