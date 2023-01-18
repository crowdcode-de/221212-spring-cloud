package io.crowdcode.cloudbay.gatewayserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
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

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }

    public static class CustomGlobalFilter implements GlobalFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return -1;
        }
    }

}
