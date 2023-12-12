package com.festival.festival.repository.custom;

import com.festival.festival.dto.NoticeDTO;

import java.util.List;

public interface NoticeRepositoryCustom {
    void modifyById(NoticeDTO dto);

    List<Object> getPrevIdxAndTitle(Long idx);
    List<Object> getNextIdxAndTitle(Long idx);
}
