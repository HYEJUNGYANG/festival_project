package com.festival.festival.repository;

import com.festival.festival.entity.Role;
import com.festival.festival.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void insertUser() throws Exception{

        IntStream.rangeClosed(51,52).forEach(i ->{
            User user = User.builder()
                    .id("admin"+i+"@gmail.com")
                    .pw(bCryptPasswordEncoder.encode("qwer"))
                    .name("이름 "+i)
                    .nick("관리자"+i)
                    .gender('M')
                    .tel("010-0000-0000")
                    .birth(LocalDate.of(2000,11,11))
                    .join_date(LocalDateTime.now())
                    .f_list("@@"+i+"@@")
                    .e_list("@@"+i+"@@")
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
        });
    }
}