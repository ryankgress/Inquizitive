package springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // Anything within the URL /employee/** will require authentication. Can include more with , separator
            .authorizeHttpRequests().requestMatchers("/employee/**").authenticated()
                // Anything else in the application is permitted
            .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginPage("/login/loginPage")
                .loginProcessingUrl("/login/loginpost")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/login/logout")         // If navigate here, logs user out
                .logoutSuccessUrl("/index");        // Kicks user to this page if logged out

        return http.build();
    }



}
