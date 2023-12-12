package com.festival.festival.repository.custom;

import com.festival.festival.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QUser user = QUser.user; // 기본 인스턴스 사용

    public UserRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Transactional //닉네임,전화번호 정보 수정
    public void modifyById(String id, String nick, String tel) {
        queryFactory
                .update(user)
                .set(user.tel, tel)
                .set(user.nick, nick)
                .where(user.id.eq(id))
                .execute();
    }

    @Transactional
    @Override
    public void updatePw(String id, String new_pw) {
        queryFactory
                .update(user)
                .set(user.pw, new_pw)
                .where(user.id.eq(id))
                .execute();
    }
}
