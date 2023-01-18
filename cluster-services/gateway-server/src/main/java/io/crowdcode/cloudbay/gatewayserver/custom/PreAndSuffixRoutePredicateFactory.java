package io.crowdcode.cloudbay.gatewayserver.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Component
public class PreAndSuffixRoutePredicateFactory extends AbstractRoutePredicateFactory<PreAndSuffixRoutePredicateFactory.Config> {

    public PreAndSuffixRoutePredicateFactory() {
        super(Config.class);
    }

    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            String path = exchange.getRequest().getPath().value();
            return path.startsWith(config.getPrefix()) && path.endsWith(config.getSuffix());
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("prefix", "suffix");
    }

    @Getter
    @Setter
    public static class Config {
        private String prefix;
        private String suffix;
    }
}
