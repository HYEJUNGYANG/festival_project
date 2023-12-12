package com.festival.festival.service;


import com.festival.festival.dto.UserDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.entity.User;

public interface UserService {
    UserDTO read(String id);
    PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO);
    Long count();

    void updatePw(String id,String new_pw);

    void update(User user);

    default User dtoToEntity(UserDTO dto) {
        User entity = User.builder()
                .id(dto.getId())
                .pw(dto.getPw())
                .name(dto.getName())
                .f_list(dto.getF_list())
                .e_list(dto.getE_list())
                .nick(dto.getNick())
                .gender(dto.getGender())
                .tel(dto.getTel())
                .birth(dto.getBirth())
                .join_date(dto.getJoin_date())
                .role(dto.getRole())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default UserDTO entityToDto(User entity) {
        UserDTO dto = UserDTO.builder()
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .f_list(entity.getF_list())
                .e_list(entity.getE_list())
                .nick(entity.getNick())
                .gender(entity.getGender())
                .tel(entity.getTel())
                .birth(entity.getBirth())
                .join_date(entity.getJoin_date())
                .role(entity.getRole())
                .build();
        return dto;
    }

    void modify_nick_tel(String id, String nick, String tel);

}
