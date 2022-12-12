package io.crowdcode.cloudbay.accounts.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@Slf4j
@Configuration
@EnableWebSecurity
@Profile("with-security")
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors((corsConfig) -> {
                    corsConfig.configurationSource((request) -> {
                                CorsConfiguration corsConfiguration = new CorsConfiguration();
                                corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
                                corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
                                corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
                                return corsConfiguration;
                            }
                    );
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeConfig) -> {
                    authorizeConfig
                            .requestMatchers("/", "/index.html", "/favicon.png").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .logout(withDefaults())
                .build();
    }

}
