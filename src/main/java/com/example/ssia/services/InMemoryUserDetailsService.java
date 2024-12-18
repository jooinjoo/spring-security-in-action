package com.example.ssia.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users; // UserDetailsService는 메모리 내 사용자 목록을 관리

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username)) // 사용자 목록에서 요청된 사용자 이름과 일치하는 항목 필터링
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
