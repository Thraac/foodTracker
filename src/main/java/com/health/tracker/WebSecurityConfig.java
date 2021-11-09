package com.health.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This cannot stay in production, This prevents security from getting in the way of the testing
        // Alter this before going live
        http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/newuser").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST,"/newuser/*").permitAll()
        .antMatchers(HttpMethod.GET,"/master/*").permitAll()
        .antMatchers(HttpMethod.GET,"/demo").permitAll()
        .antMatchers(HttpMethod.GET,"/demo/*").permitAll()
        .antMatchers(HttpMethod.POST,"/demo/*").permitAll()
        .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
        .anyRequest().authenticated();
        // http
        //         .authorizeRequests()
        //             .antMatchers("/", "/home").permitAll()
        //             .anyRequest().authenticated()
        //             .and()
        //         .formLogin()
        //             .loginPage("/login")
        //             .permitAll()
        //             .and()
        //         .logout()
        //             .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = 
            User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
