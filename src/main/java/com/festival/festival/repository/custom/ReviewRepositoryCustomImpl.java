package com.festival.festival.repository.custom;

import com.festival.festival.dto.ReviewDTO;
import com.festival.festival.entity.QReview;
import com.festival.festival.entity.Review;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QReview review = QReview.review; // 기본 인스턴스 사용

    public ReviewRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Review> getList(Long idx) {
        List<Review> dto = queryFactory
                .select(review)
                .from(review)
                .where(review.e_idx.eq(idx))
                .fetch();
        return dto;
    }

    @Override
    public List<Review> findListById(String id) {
        return queryFactory
                .selectFrom(review)
                .where(review.u_id.eq(id))
                .fetch();
    }

    @Override
    public void InsertReview(ReviewDTO dto) {
        em.createNativeQuery("INSERT INTO review (content, u_id, u_nick, star, e_idx, e_name, date) VALUES (?, ?, ?, ?, ?, ?, now())")
                .setParameter(1, dto.getContent())
                .setParameter(2, dto.getU_id())
                .setParameter(3, dto.getU_nick())
                .setParameter(4, dto.getStar())
                .setParameter(5, dto.getE_idx())
                .setParameter(6, dto.getE_name())
                .executeUpdate();
    }

    @Override
    public void updateReview(ReviewDTO dto) {
        queryFactory
                .update(review)
                .set(review.content, dto.getContent())
                .set(review.u_id, dto.getU_id())
                .set(review.u_nick, dto.getU_nick())
                .set(review.e_idx, dto.getE_idx())
                .set(review.e_name, dto.getE_name())
                .set(review.star, dto.getStar())
                .where(review.idx.eq(dto.getIdx()))
                .execute();
    }
}
