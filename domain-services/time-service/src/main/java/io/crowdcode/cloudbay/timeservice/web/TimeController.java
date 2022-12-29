package io.crowdcode.cloudbay.timeservice.web;

import io.crowdcode.cloudbay.common.AnsiColor;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class TimeController {

    @GetMapping("/now")
//    @Observed(
//            name = "time calculation",
//            contextualName = "",
//            lowCardinalityKeyValues = {"class.name", "TimeController"}
//    )
    public TimeResponse now() {
        TimeResponse timeResponse = new TimeResponse().setNow(LocalDateTime.now());
        log.info(AnsiColor.blue("GOT REQUEST AND SAY " + timeResponse.getNow()));
        return timeResponse;
    }
}
