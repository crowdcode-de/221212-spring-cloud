package io.crowdcode.cloudbay.cli;

import io.crowdcode.cloudbay.common.AnsiColor;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final TimeRemoteService timeRemoteService;
    private final GreetingRemoteService greetingRemoteService;

    @Observed
    @Scheduled(fixedRate = 10_000L)
    protected void recall() {
        log.info(AnsiColor.purple("Calling services now"));
        timeRemoteService.now();
        greetingRemoteService.fetchGreeting();
        timeRemoteService.now();
        log.info(AnsiColor.purple("Finished services now"));
    }

}
