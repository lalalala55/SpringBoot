package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.demo.roles_permissions.ApplicationPermission.*;
import static com.example.demo.roles_permissions.ApplicationRole.ADMIN;
import static com.example.demo.roles_permissions.ApplicationRole.USER;


@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests ->
                        requests.anyRequest().authenticated())
                .formLogin(login ->
                        login.loginPage("/login").permitAll())
                .rememberMe(rememberme ->
                        rememberme.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                                .key("somesecretkey")) // 21 days of validity

                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("a@123"))
                .authorities(ADMIN.getAuthorites())
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("u@123"))
                .authorities(USER.getAuthorites())
                .build();

        List<UserDetails> users = Arrays.asList( admin, user );

        return new InMemoryUserDetailsManager(users);
    }
}
