package com.festival.festival.repository.custom;

import com.festival.festival.dto.NoticeDTO;
import com.festival.festival.entity.QNotice;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QNotice notice = QNotice.notice; // 기본 인스턴스 사용

    public NoticeRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public void modifyById(NoticeDTO dto) {
        queryFactory
                .update(notice)
                .set(notice.title, dto.getTitle())
                .set(notice.content, dto.getContent())
                .set(notice.filename, dto.getFilename())
                .set(notice.filepath, dto.getFilepath())
                .where(notice.idx.eq(dto.getIdx()))
                .execute();
    }

    @Override
    public List<Object> getPrevIdxAndTitle(Long idx) {
        Tuple tuple = queryFactory
                .select(notice.idx, notice.title)
                .from(notice)
                .where(notice.idx.lt(idx))
                .orderBy(notice.idx.desc())
                .limit(1)
                .fetchOne();
        List<Object> list = new ArrayList<>();
        list.add(tuple != null ? tuple.get(notice.idx) : 0);
        list.add(tuple != null ? tuple.get(notice.title) : "");
        return list;
    }

    @Override
    public List<Object> getNextIdxAndTitle(Long idx) {
        Tuple tuple = queryFactory
                .select(notice.idx, notice.title)
                .from(notice)
                .where(notice.idx.gt(idx))
                .limit(1)
                .fetchFirst();
        List<Object> list = new ArrayList<>();
        list.add(tuple != null ? tuple.get(notice.idx) : 0);
        list.add(tuple != null ? tuple.get(notice.title) : "");
        return list;
    }

}
