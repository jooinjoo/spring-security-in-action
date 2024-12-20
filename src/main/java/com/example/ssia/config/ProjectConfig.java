package com.example.ssia.config;

import com.example.ssia.handlers.CustomAuthenticationFailureHandler;
import com.example.ssia.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableAsync
public class ProjectConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated())
                .formLogin(form -> form
//                        .defaultSuccessUrl("/home", true)); // 로그인 성공 후 사용자를 해당 URL로 리다이렉션
                        .successHandler(authenticationSuccessHandler)   // formLogin을 지원하며 인증 성공/실패 시 맞춤 구성 등록
                        .failureHandler(authenticationFailureHandler))
                .httpBasic(withDefaults()); // HTTP Basic 함께 이용
        return http.build();
    }
}
