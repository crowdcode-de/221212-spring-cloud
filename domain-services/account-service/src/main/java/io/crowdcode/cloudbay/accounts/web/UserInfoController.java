package io.crowdcode.cloudbay.accounts.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class UserInfoController {

    @GetMapping("/userinfo")
    public ResponseEntity<String> username(Authentication authentication) {
        log.info(("{}"), authentication);
        return ResponseEntity.ok("{\"username\":\"" + nameOfCurrentUser(authentication) + "\"}");
    }

    @GetMapping("/roles")
    public ResponseEntity<String> roles(@RequestHeader Map<String, String> headers, Authentication authentication) {
        log.info("{}", authentication);
        headers.forEach((key, value) -> log.info("Headers: {} = {}", key, value));

        var roles = authentication.getAuthorities().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\",\"", "\"", "\""));
        return ResponseEntity.ok("{\"roles\": [" + roles + "]}");
    }

    /**
     * * @param authentication
     *
     * @return Full Username
     */
    public String nameOfCurrentUser(Authentication authentication) {
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getFullName)
                .orElseGet(authentication::getName);
    }

}
