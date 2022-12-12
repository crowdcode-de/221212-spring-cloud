package io.crowdcode.cloudbay.greetingservice.web;

import io.crowdcode.cloudbay.greetingservice.config.GreetingProperties;
import io.crowdcode.cloudbay.greetingservice.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingProperties greetingProperties;

    private final TimeService timeService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return greetingProperties.getWelcomeMessage() + " at " + timeService.retrieveNow();
    }

    @GetMapping("/sayGoodBye")
    public String sayGoodBye() {
        return greetingProperties.getGoodbyeMessage() + " at " + timeService.retrieveNow();
    }

}
