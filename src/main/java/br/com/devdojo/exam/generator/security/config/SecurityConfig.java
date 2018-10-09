package br.com.devdojo.exam.generator.security.config;

import br.com.devdojo.exam.generator.security.filter.JWTAuthorizationFilter;
import br.com.devdojo.exam.generator.security.service.CustomUserDetailsService;
import br.com.devdojo.exam.generator.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*/professor/**").hasRole("PROFESSOR")
                .antMatchers("/*/student/**").hasRole("STUDENT")
                .and()
                .addFilter(new JwtAuthenticationFilter (authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),customUserDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}