package com.example.securitytest.service;

import com.example.securitytest.dto.UserDto;
import com.example.securitytest.entity.User;
import com.example.securitytest.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;


    public void saveUser(UserDto userDto,PasswordEncoder passwordEncoder){
        User user = User.createUser(userDto,passwordEncoder );
        userRepo.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUserId(username);
        if(user ==null){
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUserId())
                .password(user.getUserPw())
                .roles(user.getRole())
                .build();

    }
}











