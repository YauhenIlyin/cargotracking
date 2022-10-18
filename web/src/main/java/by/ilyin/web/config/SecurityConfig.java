package by.ilyin.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/about", "/api/users", "/api/users/*").permitAll();

        return http.build();
        /*
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/about").permitAll()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.GET, "/api/users")
                .hasAnyRole(ROLE_SYS_ADMIN.name(),ROLE_ADMIN.name(),ROLE_DISPATCHER.name())
                .antMatchers("/api/users").hasAnyRole(ROLE_SYS_ADMIN.name(),ROLE_ADMIN.name());

        return http.build();
         */
    }

}
