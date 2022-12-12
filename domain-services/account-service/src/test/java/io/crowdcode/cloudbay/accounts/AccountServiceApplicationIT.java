package io.crowdcode.cloudbay.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@WithMockUser("customer")
@SpringBootTest
class AccountServiceApplicationIT {

    @Test
    void spring_context_can_be_created(ApplicationContext context) {
        assertThat(context).isNotNull();
    }
}
