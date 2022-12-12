package io.crowdcode.cloudbay.gatewayserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Configuration
public class LoggingGlobalFiltersConfiguration {

    @Bean
    public GlobalFilter postLoggingGatewayFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() ->
                        log.info("Global Post Filter executed")
                ));
    }
}
