package com.festival.festival.repository.custom;

import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.entity.QReserve;
import com.festival.festival.entity.Reserve;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class ReserveRepositoryCustomImpl implements ReserveRepositoryCustom{
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    private QReserve reserve = QReserve.reserve; // 기본 인스턴스 사용

    public ReserveRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public void modifyById(ReserveDTO dto) {
        queryFactory
                .update(reserve)
                .set(reserve.state, dto.getState())
                .where(reserve.num.eq(dto.getNum()))
                .execute();
    }

    @Override
    public void modifyReviewYon(ReserveDTO dto) {
        queryFactory
                .update(reserve)
                .set(reserve.review,('y'))
                .where(reserve.num.eq(dto.getNum()))
                .execute();
    }

    @Override
    public void modifyCancel(int num) {
        queryFactory
                .update(reserve)
                .set(reserve.state, "취소요청")
                .where(reserve.num.eq(num))
                .execute();
    }

    @Override
    public Optional<Reserve> getList(int num) {
        Reserve reserve = queryFactory
                .select(this.reserve)
                .from(this.reserve)
                .where(this.reserve.num.eq(num))
                .fetchFirst();
        return Optional.ofNullable(reserve);
    }

    @Override
    public List<Reserve> findById(String id) {
        return queryFactory
                .selectFrom(reserve)
                .where(reserve.u_id.eq(id))
                .fetch();
    }

    @Override
    public Long countBystate(String cancel, String fin) {
        return queryFactory
                .selectFrom(reserve)
                .where(reserve.state.ne(cancel))
                .where(reserve.state.ne(fin))
                .fetchCount();
    }

    @Override // idx를 기준, desc로 3개의 데이터를 꺼내옴
    public List<Reserve> findTop3ByOrderByIdDesc(String cancel) {
        List<Reserve> dto = queryFactory
                .selectFrom(reserve)
                .where(reserve.state.eq(cancel))
                .orderBy(reserve.now_date.desc())
                .limit(3)
                .fetch();
        return dto;
    }

    @Override
    public List<Integer> getNumList() {
        return queryFactory
                .select(reserve.num)
                .from(reserve)
                .fetch();
    }

    @Override
    public void insertReserv(ReserveDTO reserveDTO) {
        em.createNativeQuery("insert into reserve (reserve_num, date, now_date, e_name, e_price, e_idx, pay, review, state, u_id, u_tel, u_name, total, count) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, reserveDTO.getNum())
                .setParameter(2, reserveDTO.getDate())
                .setParameter(3, reserveDTO.getNow_date())
                .setParameter(4, reserveDTO.getE_name())
                .setParameter(5, reserveDTO.getE_price())
                .setParameter(6, reserveDTO.getE_idx())
                .setParameter(7, reserveDTO.getPay())
                .setParameter(8, reserveDTO.getReview())
                .setParameter(9, reserveDTO.getState())
                .setParameter(10, reserveDTO.getU_id())
                .setParameter(11, reserveDTO.getU_tel())
                .setParameter(12, reserveDTO.getU_name())
                .setParameter(13, reserveDTO.getTotal())
                .setParameter(14, reserveDTO.getCount())
                .executeUpdate();
    }
}
