package io.crowdcode.cloudbay.cli;

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
public class TimeRemoteService {

    private final RestTemplate restTemplate;

    @Value("${io.crowdcode.services.time.url}")
    private String timeServiceUrl;

    @Observed(
            name = "time.service.now",
            contextualName = "call-now-methods",
            lowCardinalityKeyValues = {"call-number", "42"}
    )
    public String now() {
        log.info(AnsiColor.purple("Sending request to the time service"));
        String response = restTemplate.getForObject(timeServiceUrl, String.class);
        log.info(AnsiColor.blue("Received: {}"), response);
        return response;
    }

}
