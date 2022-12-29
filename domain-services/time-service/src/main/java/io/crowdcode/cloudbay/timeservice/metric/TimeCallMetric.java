package io.crowdcode.cloudbay.timeservice.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Aspect
@Component
@Profile("with-actuator")
@RequiredArgsConstructor
public class TimeCallMetric {

    private final MeterRegistry meterRegistry;

    private Counter timeCounter;

    @PostConstruct
    public void init() {
        timeCounter = Counter.builder("timer.time_count")
                .tag("type", "total")
                .description("Total number of time requests")
                .register(meterRegistry);
    }

    @Before("execution(* io.crowdcode.cloudbay.timeservice.web.*.now(..))")
    public void countNowEvent() {
        timeCounter.increment();
    }


}
