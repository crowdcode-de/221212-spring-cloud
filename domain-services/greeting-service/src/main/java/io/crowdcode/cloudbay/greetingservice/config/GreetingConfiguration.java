package io.crowdcode.cloudbay.greetingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Configuration
public class GreetingConfiguration {

    @Bean
    @ConfigurationProperties(value = "io.crowdcode.greeting", ignoreUnknownFields = false)
    public GreetingProperties greetingProperties() {
        return new GreetingProperties();
    }
}
