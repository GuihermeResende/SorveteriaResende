package com.comm.SorveteriaResende.Security;

import com.comm.SorveteriaResende.Security.JWT.Handler.AccessDeniedHandler;
import com.comm.SorveteriaResende.Security.JWT.Handler.UnauthorizedHandler;
import com.comm.SorveteriaResende.Security.JWT.JwtAuthenticationFilter;
import com.comm.SorveteriaResende.Security.JWT.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedHandler unauthorizedHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager();

        List<String> origins = new ArrayList<>();
        origins.add("http://localhost:4200");
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues().setAllowedOriginPatterns(
               origins
        ));
        http
            .authorizeRequests()
            .antMatchers( "/api/v1/login").permitAll()
            .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
            .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
            .permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .addFilter(new JwtAuthenticationFilter(authManager))
            .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}

