package io.crowdcode.cloudbay.greetingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TimeService {

    private final RestTemplate restTemplate;

    @Value("${io.crowdcode.services.time.url}")
    private String timeServiceUrl;

    @Retryable
    public LocalDateTime retrieveNow() {
        ResponseEntity<TimeResponse> responseEntity = restTemplate
                .getForEntity(timeServiceUrl, TimeResponse.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(TimeResponse::getNow)
                .orElse(LocalDateTime.MIN);
    }
    @Recover
    public LocalDateTime retrieveNowFallback() {
        log.warn("Fallback method called");
        return LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

}
