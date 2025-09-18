package com.lak.secure.service;

import com.lak.secure.entity.UserEntity;
import com.lak.secure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        return userDetails;
    }
}
