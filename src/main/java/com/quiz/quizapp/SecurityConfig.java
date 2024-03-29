package com.quiz.quizapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.formLogin(form->form.loginPage("/login"))
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin").authenticated().anyRequest().permitAll()
            )
            .httpBasic(withDefaults())
            .csrf().disable();
        return http.build();
    }
    
    @Bean
    public PasswordEncoder PasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
            .withUsername("chayan")
            .password(PasswordEncoder().encode("chayan"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }


}
