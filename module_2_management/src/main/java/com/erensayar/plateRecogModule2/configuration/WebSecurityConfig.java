package com.erensayar.plateRecogModule2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/customCss/**",
                        "/customJs/**",
                        "/webjars/**",
                        "/checkplate",
                        "/img/**").permitAll()
                .antMatchers(
                        "/home",
                        "/addplate",
                        "/addedplate").access("hasRole('ADMIN')")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable();

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user
                = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
