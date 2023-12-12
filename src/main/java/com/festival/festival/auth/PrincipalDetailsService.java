package com.festival.festival.auth;

import com.festival.festival.entity.User;
import com.festival.festival.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("========================username:"+id+"========================");
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return new PrincipalDetails(optionalUser.get());
        }
        throw new UsernameNotFoundException("User not found with username: " + id); // 사용자를 찾지 못했다면 이 예외를 발생시킵니다.

    }
}
