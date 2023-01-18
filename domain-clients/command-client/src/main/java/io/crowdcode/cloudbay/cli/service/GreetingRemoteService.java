package io.crowdcode.cloudbay.cli.service;

import io.crowdcode.cloudbay.common.AnsiColor;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GreetingRemoteService {

    private final RestTemplate restTemplate;

    @Value("${io.crowdcode.services.greeting.welcome-url}")
    private String welcomeServiceUrl;

    @Observed(
            name = "greeting.service.fetch-greetings",
            contextualName = "fetch-greeting-methods"
    )
    public String fetchGreeting() {
        log.info(AnsiColor.purple("Sending request to the greeting service"));
        String response = restTemplate.getForObject(welcomeServiceUrl, String.class);
        log.info(AnsiColor.blue("Received: {}"), response);
        return response;
    }

}
