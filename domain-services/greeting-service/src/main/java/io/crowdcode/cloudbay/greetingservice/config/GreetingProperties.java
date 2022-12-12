package io.crowdcode.cloudbay.greetingservice.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Data
@Slf4j
//@Component
//@ConfigurationProperties(prefix = "io.crowdcode.greeting")
public class GreetingProperties {

    public GreetingProperties() {
        log.info("--------- RECREATE GREETING PROPERTIES -------");
    }

    /**
     * This is the message that is shown after login
     */
    private String welcomeMessage = "Guten Tag";

    /**
     * This is the message that is shown after logout
     */
    private String goodbyeMessage = "Tschüss";

    /**
     * Defines how long should the toast message be shown in seconds
     */
    private int visibleInSeconds = 5;

    private String isNotSet;

}
