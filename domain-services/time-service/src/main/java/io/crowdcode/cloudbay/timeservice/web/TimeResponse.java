package io.crowdcode.cloudbay.timeservice.web;

import java.time.LocalDateTime;

public class TimeResponse {

    private LocalDateTime now;

    public LocalDateTime getNow() {
        return now;
    }

    public TimeResponse setNow(LocalDateTime now) {
        this.now = now;
        return this;
    }
}
