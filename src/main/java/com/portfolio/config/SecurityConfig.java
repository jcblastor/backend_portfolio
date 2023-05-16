package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portfolio.security.CustomUserDetailsService;
import com.portfolio.security.JwtAuthenticationEntryPoint;
import com.portfolio.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();

    return http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests().antMatchers(HttpMethod.GET, "/api/**")
            .permitAll()
            .antMatchers("/api/auth/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilter(jwtAuthenticationFilter)
            .build();
  }

  /* @Bean
  UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(
      User.withUsername("carlos")
      .password(passwordEncoder().encode("admin"))
      .roles("ADMIN")
      .build()
    );

    manager.createUser(
      User.withUsername("olga")
      .password(passwordEncoder().encode("user"))
      .roles("USER")
      .build()
    );

    return manager;
  } */

  @Bean
  AuthenticationManager authManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder())
          .and()
          .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
