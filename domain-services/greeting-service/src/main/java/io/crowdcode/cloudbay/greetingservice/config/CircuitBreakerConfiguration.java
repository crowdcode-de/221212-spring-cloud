package io.crowdcode.cloudbay.greetingservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@EnableRetry
@Configuration
@Profile("circuit-breaker-retry")
public class CircuitBreakerConfiguration {
}
