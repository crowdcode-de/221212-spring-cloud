package io.crowdcode.cloudbay.greetingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Configuration
public class GreetingConfiguration {

    @Bean
    @RefreshScope
    @ConfigurationProperties(value = "io.crowdcode.greeting", ignoreUnknownFields = false)
    public GreetingProperties greetingProperties() {
        return new GreetingProperties();
    }
}
