package com.festival.festival.repository.custom;

import com.festival.festival.dto.ExpDTO;
import com.festival.festival.entity.Exp;
import com.festival.festival.entity.Festival;
import com.festival.festival.entity.QExp;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ExpRepositoryCustomImpl implements ExpRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QExp exp = QExp.exp; // 기본 인스턴스 사용

    public ExpRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void modifyById(ExpDTO dto) {
        queryFactory
                .update(exp)
                .set(exp.name, dto.getName())
                .set(exp.zone, dto.getZone())
                .set(exp.l_name, dto.getL_name())
                .set(exp.detail, dto.getDetail())
                .set(exp.place, dto.getPlace())
                .set(exp.tel, dto.getTel())
                .set(exp.link, dto.getLink())
                .set(exp.time, dto.getTime())
                .set(exp.price, dto.getPrice())
                .set(exp.content, dto.getContent())
                .set(exp.warning, dto.getWarning())
                .set(exp.tag, dto.getTag())
                .set(exp.latitude, dto.getLatitude())
                .set(exp.hardness, dto.getHardness())
                .where(exp.idx.eq(dto.getIdx()))
                .execute();
    }

    @Override
    public List<Exp> getAllByZone(String zone) {
        List<Exp> dto = queryFactory
                .select(exp)
                .from(exp)
                .where(exp.zone.eq(zone))
                .orderBy(exp.idx.desc())
                .fetch();
        return dto;
    }

    @Override
    public void modifyCount(Long count, Long idx) {
        queryFactory
                .update(exp)
                .set(exp.count, exp.count.add(-count))
                .where(exp.idx.eq(idx))
                .execute();
    }

    @Override // idx를 기준, desc로 3개의 데이터를 꺼내옴
    public List<Exp> findTop3ByOrderByIdDesc() {
        List<Exp> dto = queryFactory
                .selectFrom(exp)
                .orderBy(exp.idx.desc())
                .limit(3)
                .fetch();
        return dto;
    }

    @Override
    public List<Exp> getListByIdxs(int[] idxList) {
        List<Exp> list = new ArrayList<>();
        for (int i=0; i<idxList.length; i++) {
            list.add(queryFactory
                    .select(exp)
                    .from(exp)
                    .where(exp.idx.eq((long) idxList[i]))
                    .fetchFirst());
        }
        log.info("마이페이지 관심목록 리스트 확인" + list);
        return list;
    }
}
