package io.crowdcode.cloudbay.cli.config;

import io.crowdcode.cloudbay.cli.service.TimeRemoteService;
import io.crowdcode.cloudbay.common.AnsiColor;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Configuration
public class ObservationConfiguration {

    /**
     * Needed to be instrumented by observation services
     *
     * @param builder
     * @return restTemplate
     */
    @Bean
    @ConditionalOnMissingBean(value = RestTemplate.class)
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        Predicate<ProceedingJoinPoint> logAll = pjp -> {
            log.info(AnsiColor.yellow("Observing: {}"), pjp.toShortString());
            return false;
        };
        return new ObservedAspect(observationRegistry, logAll);
    }

    @Bean
    ApplicationRunner applicationRunner(ObservationRegistry registry, TimeRemoteService timeService) {
        Random highCardinalityValues = new Random(); // Simulates potentially large number of values

        return (args) -> {
            String userId = String.valueOf(highCardinalityValues.nextLong(100_000));
            Observation.createNotStarted("time.observation", registry)
                    .contextualName("cli-runner")
                    .lowCardinalityKeyValue("userTyoe", "my-user-type")
                    .highCardinalityKeyValue("userID", userId)
                    .observe(() -> {
                        log.info(AnsiColor.green("observed"));
                        timeService.now();
                        log.info(AnsiColor.green("observed"));
                    });
        };
    }

}
