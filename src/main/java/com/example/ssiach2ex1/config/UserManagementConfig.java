package com.example.ssiach2ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserManagementConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var user = User.withUsername("john")
                // {noop}를 붙여, Spring Security가 내부적으로 NoOpPasswordEncoder를 암묵적으로 사용해, PasswordEncoder를 빈 등록 필요가 없음.
                // 만약 {noop}를 뺀다면, java.lang.IllegalArgumentException 발생.
                .password("{noop}12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
