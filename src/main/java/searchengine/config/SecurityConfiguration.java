package searchengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.crypto.password.NoOpPasswordEncoder.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder) {
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("admin", "user").build();
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("user"))
                .roles("user").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    public PasswordEncoder passwordEncoder() {
        return getInstance();
    }

    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .formLogin().permitAll().and().authorizeRequests().anyRequest().hasRole("admin")
                .and()
                .csrf().disable()
                .build();
    }
}
