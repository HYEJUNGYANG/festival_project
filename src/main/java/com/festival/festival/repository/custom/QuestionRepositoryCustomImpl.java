package com.festival.festival.repository.custom;

import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.entity.QNotice;
import com.festival.festival.entity.QQuestion;
import com.festival.festival.entity.Question;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QQuestion question = QQuestion.question; // 기본 인스턴스 사용

    public QuestionRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public void InsertQuestion(QuestionDTO dto) {
        em.createNativeQuery("INSERT INTO question (priv, q_content, title, u_id, u_nick, yn, date) VALUES (?, ?, ?, ?, ?, ?, now())")
                .setParameter(1, dto.getPriv())
                .setParameter(2, dto.getQ_content())
                .setParameter(3, dto.getTitle())
                .setParameter(4, dto.getU_id())
                .setParameter(5, dto.getU_nick())
                .setParameter(6, dto.getYn())
                .executeUpdate();
    }

    @Override
    public void updateQuestion(QuestionDTO dto) {
        queryFactory
                .update(question)
                .set(question.title, dto.getTitle())
                .set(question.q_content, dto.getQ_content())
                .set(question.priv, dto.getPriv())
                .set(question.u_nick, dto.getU_nick())
                .where(question.idx.eq(dto.getIdx()))
                .execute();
    }

    @Override
    public List<Question> findTop3ByOrderByIdDesc(char yn) {
        List<Question> dto = queryFactory
                .selectFrom(question)
                .where(question.yn.eq(yn))
                .orderBy(question.idx.desc())
                .limit(3)
                .fetch();
        return dto;
    }


}
