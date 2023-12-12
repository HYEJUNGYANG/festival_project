package com.festival.festival.repository.custom;


import com.festival.festival.dto.FestivalDTO;
import com.festival.festival.entity.Festival;

import java.util.HashMap;
import java.util.List;

public interface FestivalRepositoryCustom {
    void modifyById(Long id, FestivalDTO dto);
    List<Festival> getAllByZone(String zone);

    List<Festival> getFestivalListByKeyword(HashMap<String, Object> map);

    List<Festival> findTop3ByOrderByIdDesc();// idx를 기준, desc로 3개의 데이터를 꺼내옴

    List<Festival> getListByIdxs(int[] idxList);
}
