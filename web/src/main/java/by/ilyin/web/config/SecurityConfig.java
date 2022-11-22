package by.ilyin.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyRole("ADMIN", "DISPATCHER")
                .antMatchers(HttpMethod.POST, "/api/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("ADMIN")
<<<<<<< HEAD

                .antMatchers(HttpMethod.GET, "/api/profile").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.PUT, "/api/profile").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.PUT, "/api/profile/change-password").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                
                .antMatchers("/api/sign-in", "api/logout", "/api/refresh").permitAll()

                .antMatchers(HttpMethod.POST, "/api/email").permitAll()
                .antMatchers(HttpMethod.POST, "/api/email/repairing").permitAll()
                .antMatchers(HttpMethod.GET, "/api/restore").permitAll()
                .antMatchers(HttpMethod.POST, "/api/profile/change-email").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.GET, "/api/profile/confirm-change-email/{uuid}").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")

=======
                .antMatchers("api/logout").authenticated()
                .antMatchers("/api/sign-in", "/api/refresh").permitAll()
>>>>>>> b5f572a (CTB-6_JWT_authentication is created)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
