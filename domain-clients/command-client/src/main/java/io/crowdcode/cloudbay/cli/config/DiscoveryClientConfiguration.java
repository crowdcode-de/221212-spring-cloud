package io.crowdcode.cloudbay.cli.config;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.crowdcode.cloudbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ingo Düppe (CROWDCODE)
 */

@Slf4j
@Configuration
@Profile("with-discovery")
@EnableDiscoveryClient
public class DiscoveryClientConfiguration {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> onContextRefreshForDiscovery(final EurekaClient discoveryClient) {
        return (event) -> {
            Application application = discoveryClient.getApplication("TIME-SERVICE");

            if (application == null) {
                log.error(AnsiColor.red("no time service found."));
                return;
            }

            application.getInstances().stream()
                    .map(i -> "http://" + i.getHostName() + ":" + i.getPort() + "/now is " + i.getStatus() )
                    .map(AnsiColor::blue)
                    .forEach(log::info);
        };
    }

}
