package io.crowdcode.cloudbay.gatewayserver.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static io.crowdcode.cloudbay.common.AnsiColor.yellow;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(yellow("Global Pre Filter executed"));
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() ->
                        log.info(yellow("Global Post Filter executed"))
                ));
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
