package io.crowdcode.cloudbay.cli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class CliClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CliClientApplication.class, args);
    }

}
