package com.festival.festival.repository.custom;

import com.festival.festival.dto.FestivalDTO;
import com.festival.festival.entity.Festival;
import com.festival.festival.entity.QFestival;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class FestivalRepositoryCustomImpl implements FestivalRepositoryCustom{

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QFestival festival = QFestival.festival; // 기본 인스턴스 사용

    public FestivalRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Festival> getAllByZone(String zone) {
        List<Festival> dto = queryFactory
                .select(festival)
                .from(festival)
                .where(festival.zone.eq(zone))
                .orderBy(festival.idx.desc())
                .fetch();
        return dto;
    }

    @Override
    public void modifyById(Long id, FestivalDTO dto) {
        // 나중에 파일 이름, 경로 추가하기
        queryFactory
                .update(festival)
                .set(festival.name, dto.getName())
                .set(festival.zone, dto.getZone())
                .set(festival.detail, dto.getDetail()) // 2023.11.29 추가 (하....)
                .set(festival.l_name, dto.getL_name())
                .set(festival.place, dto.getPlace())
                .set(festival.tel, dto.getTel())
                .set(festival.link, dto.getLink())
                .set(festival.time, dto.getTime())
                .set(festival.price, dto.getPrice())
                .set(festival.start, dto.getStart())
                .set(festival.end, dto.getEnd())
                .set(festival.latitude, dto.getLatitude())
                .set(festival.hardness, dto.getHardness())
                .where(festival.idx.eq(id))
                .execute();
    }

    @Override
    public List<Festival> getFestivalListByKeyword(HashMap<String, Object> map) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!map.get("area").toString().isEmpty()) {
            List<String> zones = (List<String>) map.get("area");
            if(zones != null) {
                for(String zone : zones) {
                    builder.and(festival.zone.eq(zone));
                }
            }
        }
        builder.and(festival.name.contains(map.get("keyword").toString())
                .or(festival.detail.contains(map.get("keyword").toString())));
        if(!map.get("date").toString().isEmpty()) {
            builder.and(festival.start.loe(LocalDate.parse(map.get("date").toString())));
            builder.and(festival.end.goe(LocalDate.parse(map.get("date").toString())));
        }

        List<Festival> festivalDTO = queryFactory
                .select(festival)
                .from(festival)
                .where(builder)
                .fetch();

        log.info("festival 검색 결과: " + festivalDTO);

        return festivalDTO;
    }

    @Override
    public List<Festival> findTop3ByOrderByIdDesc() {
        List<Festival> dto = queryFactory
                .selectFrom(festival)
                .orderBy(festival.idx.desc())
                .limit(3)
                .fetch();
        return dto;
    }

    @Override
    public List<Festival> getListByIdxs(int[] idxList) {
        List<Festival> list = new ArrayList<>();
        for (int i=0; i<idxList.length; i++) {
            list.add(queryFactory
                    .select(festival)
                    .from(festival)
                    .where(festival.idx.eq((long) idxList[i]))
                    .fetchFirst());
        }
        log.info("마이페이지 관심목록 리스트 확인" + list);
        return list;
    }
}
