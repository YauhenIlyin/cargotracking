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

                .antMatchers(HttpMethod.GET, "/api/profile").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.PUT, "/api/profile").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.PUT, "/api/profile/change-password").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")

                .antMatchers("/api/sign-in", "api/logout", "/api/refresh").permitAll()

                .antMatchers(HttpMethod.POST, "/api/email").permitAll()
                .antMatchers(HttpMethod.POST, "/api/email/repairing").permitAll()
                .antMatchers(HttpMethod.GET, "/api/restore").permitAll()

                .antMatchers(HttpMethod.POST, "/api/profile/change-email").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.GET, "/api/profile/confirm-change-email/{uuid}").hasAnyRole("COMPANY_OWNER", "DISPATCHER", "MANAGER", "DRIVER")
                .antMatchers(HttpMethod.POST, "/api/template").hasRole("ADMIN")

                .antMatchers("api/logout").authenticated()
                .antMatchers("/api/sign-in", "/api/refresh").permitAll()

                .antMatchers("api/logout").authenticated()
                .antMatchers("/api/sign-in", "/api/refresh").permitAll()

                .antMatchers(HttpMethod.POST, "/api/clients").hasRole("SYS_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clients/{id}").hasRole("SYS_ADMIN")
                .antMatchers(HttpMethod.GET, "/api/clients").hasRole("SYS_ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/clients/activate/{clientId}").hasRole("SYS_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/clients").hasRole("SYS_ADMIN")
                
                .antMatchers(HttpMethod.POST, "/api/storages").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/storages").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/storages/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/storages").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/storages").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/product-owners").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/product-owners").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/product-owners/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/product-owners").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/product-owners").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/cars").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cars").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cars/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/cars").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/cars").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/logout").authenticated()
                .antMatchers(HttpMethod.POST, "/api/sign-in", "/api/refresh").permitAll()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
