package com.festival.festival.dto;

import com.festival.festival.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String id;
    private String pw;
    private String name;
    private String nick;
    private char gender;
    private String tel;
    private String f_list;
    private String e_list;
    private LocalDate birth;
    private LocalDateTime join_date;
    private Role role;

}
