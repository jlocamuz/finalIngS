package com.mycompany.myapp.config;


import com.mycompany.myapp.security.*;
import com.mycompany.myapp.security.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {


   private final JHipsterProperties jHipsterProperties;


   private final TokenProvider tokenProvider;


   private final CorsFilter corsFilter;
   private final SecurityProblemSupport problemSupport;


   public SecurityConfiguration(
       TokenProvider tokenProvider,
       CorsFilter corsFilter,
       JHipsterProperties jHipsterProperties,
       SecurityProblemSupport problemSupport
   ) {
       this.tokenProvider = tokenProvider;
       this.corsFilter = corsFilter;
       this.problemSupport = problemSupport;
       this.jHipsterProperties = jHipsterProperties;
   }


   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }


   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       // @formatter:off
       http
           .csrf()
           .disable();
       return http.build();
       // @formatter:on
   }


   private JWTConfigurer securityConfigurerAdapter() {
       return new JWTConfigurer(tokenProvider);
   }
}


