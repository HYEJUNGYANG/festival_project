package com.festival.festival.repository.custom;

import com.festival.festival.dto.AnswerDTO;
import com.festival.festival.entity.QAnswer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AnswerRepositoryCustomImpl implements AnswerRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QAnswer answer = QAnswer.answer; // 기본 인스턴스 사용

    public AnswerRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void modifyById(AnswerDTO dto) {
        queryFactory
                .update(answer)
                .set(answer.a_content, dto.getA_content())
                .where(answer.idx.eq(dto.getIdx()))
                .execute();
    }
}
