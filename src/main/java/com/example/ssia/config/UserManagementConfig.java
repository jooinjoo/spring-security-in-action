package com.example.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserManagementConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails u = User.withUsername("bill")
                .password("{noop}12345")
                .authorities("read", "write")
                .accountExpired(false)
                .disabled(true) // false로 바꿔주면 요청 가능
                .build();

        return new InMemoryUserDetailsManager(u);
    }
}
