package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.entity.User;
import com.festival.festival.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<User> result = userRepository.findAll( pageable);
        Function<User, UserDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn );
    }

    @Override
    public Long count() {
        Long userCount = userRepository.count();
        return userCount;
    }

    @Override
    public void updatePw(String id, String new_pw) {
        userRepository.updatePw(id,new_pw);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override //닉네임, 전화번호 정보 수정
    public void modify_nick_tel(String id, String nick, String tel) {
        userRepository.modifyById(id,nick,tel);
    }

    @Override
    public UserDTO read(String id) {
        Optional<User> result = userRepository.findById(id);

        return result.isPresent()? entityToDto(result.get()): null;
    }

}
