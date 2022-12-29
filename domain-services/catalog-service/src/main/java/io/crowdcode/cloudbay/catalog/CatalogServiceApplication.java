package io.crowdcode.cloudbay.catalog;

import io.crowdcode.cloudbay.catalog.domain.Product;
import io.crowdcode.cloudbay.catalog.repository.ProductRepository;
import io.crowdcode.cloudbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@SpringBootApplication
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

    @Bean
    @Profile("dummydata")
    public CommandLineRunner createProducts(ProductRepository productRepository) {
        return (args) -> {
            productRepository.save(new Product().setSku(UUID.randomUUID().toString()).setTitle("Krefelder").addTags("Bier", "Cola"));
            productRepository.save(new Product().setSku(UUID.randomUUID().toString()).setTitle("Kakao").addTags("Kakao", "CHILD"));
            productRepository.save(new Product().setSku(UUID.randomUUID().toString()).setTitle("Milch").addTags("Milch", "CHILD"));
            productRepository.save(new Product().setSku(UUID.randomUUID().toString()).setTitle("Kaffee").addTags("Kaffee", "admin"));
        };
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> onApplicationRefresh() {
        return (event) -> {
            log.info(AnsiColor.green("Context Has Been Refreshed"));
            var context = event.getApplicationContext();
            String beans = Arrays.stream(context.getBeanDefinitionNames())
                    .map(AnsiColor::blue)
                    .map(n -> "> " + n)
                    .collect(Collectors.joining("\n"));
            log.info("\n{}", beans);
        };
    }

}
