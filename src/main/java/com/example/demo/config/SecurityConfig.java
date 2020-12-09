package com.example.demo.config;

import com.example.demo.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(this.authenticationService);
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/signup","/css/**","/js/**").permitAll()
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/login")
            .permitAll();

        http.formLogin()
            .defaultSuccessUrl("/home", true);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> users= new ArrayList<UserDetails>();
        users.add(User.withUsername("admin").password("nimda").roles("USER","ADMIN").build());
        return new InMemoryUserDetailsManager(users);
    }
}
