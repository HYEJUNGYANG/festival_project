package com.festival.festival.repository.custom;


import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.entity.Reserve;

import java.util.List;
import java.util.Optional;

public interface ReserveRepositoryCustom {
    void modifyById(ReserveDTO dto);

    void modifyReviewYon(ReserveDTO dto);
    void modifyCancel(int num);

    Optional<Reserve> getList(int num);

    List<Reserve> findById(String id);

    Long countBystate(String state,String state2);

    List<Reserve> findTop3ByOrderByIdDesc(String cancel);// idx를 기준, desc로 3개의 데이터를 꺼내옴

    List<Integer> getNumList();

    void insertReserv(ReserveDTO reserveDTO);
}
