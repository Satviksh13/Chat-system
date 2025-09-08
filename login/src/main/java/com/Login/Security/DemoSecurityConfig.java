package com.Login.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails satvik = User.builder()
                .username("satvik")
                .password("{noop}test123")
                .roles("USER")
                .build();
        UserDetails chudel = User.builder()
                .username("chudel")
                .password("{noop}test123")
                .roles("USER")
                .build();
        UserDetails dhuie = User.builder()
                .username("dhuie")
                .password("{noop}test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(satvik , chudel , dhuie);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/home")
                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .formLogin(form->
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home" , true)
                                .permitAll()
                )
                .logout(logout->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll());
        return http.build();
    }
}
