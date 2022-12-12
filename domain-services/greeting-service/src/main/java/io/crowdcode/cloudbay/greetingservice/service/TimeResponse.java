package io.crowdcode.cloudbay.greetingservice.service;

import java.time.LocalDateTime;

public class TimeResponse implements Time {

    private LocalDateTime now;

    @Override
    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
}
