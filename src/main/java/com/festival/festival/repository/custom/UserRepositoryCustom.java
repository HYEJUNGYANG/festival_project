package com.festival.festival.repository.custom;


public interface UserRepositoryCustom {
    void modifyById(String id, String nick, String tel); //닉네임,전화번호 정보 수정

    void updatePw(String id,String new_pw);
}
