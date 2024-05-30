package api.csrf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers("/csrf", "/post").permitAll()
                        .pathMatchers("/ju").authenticated()
                        .anyExchange().authenticated()
                )
                .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }
}
