package com.health.tracker;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This cannot stay in production, This prevents security from getting in the way of the testing
        // Alter this before going live
        http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/hello").authenticated()
        .antMatchers(HttpMethod.GET, "/addFood").permitAll()
        .antMatchers(HttpMethod.POST, "/addFood").permitAll()
        .antMatchers(HttpMethod.GET, "/allFood").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/*").permitAll()
        .antMatchers(HttpMethod.POST,"/newuser").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST,"/newuser/*").permitAll()
        .antMatchers(HttpMethod.GET,"/master/*").permitAll()
        .antMatchers(HttpMethod.GET,"/demo").permitAll()
        .antMatchers(HttpMethod.GET,"/demo/*").permitAll()
        .antMatchers(HttpMethod.POST,"/demo/*").permitAll()
        .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
        // .anyRequest().authenticated()

        .antMatchers("/home").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();

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
}
