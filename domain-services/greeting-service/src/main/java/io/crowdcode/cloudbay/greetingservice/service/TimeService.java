package io.crowdcode.cloudbay.greetingservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TimeService {

    private final RestTemplate restTemplate;

    @Value("${time.service.url}")
    private String timeServiceUrl;

    @HystrixCommand(fallbackMethod = "defaultNow")
    public LocalDateTime retrieveNow() {
        ResponseEntity<TimeResponse> responseEntity = restTemplate
                .getForEntity(timeServiceUrl, TimeResponse.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(TimeResponse::getNow)
                .orElseGet(() -> LocalDateTime.MIN);
    }

    public LocalDateTime defaultNow() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }
}
