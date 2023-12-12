package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.entity.Reserve;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReserveService{

    ReserveDTO read(int num);

    void insertReserve(ReserveDTO reserveDTO);

    PageResultDTO<ReserveDTO, Reserve> getList(PageRequestDTO requestDTO);

    Long count(String state, String state2);
    //취소 대기중인 최신글 3개 꺼내오기
    public List<Reserve> getTop3List(String cancel);
    ReserveDTO getList(int num);

    default Reserve dtoToEntity(ReserveDTO dto) {
        Reserve entity = Reserve.builder()
                .num(dto.getNum())
                .u_name(dto.getU_name())
                .u_id(dto.getU_id())
                .u_tel(dto.getU_tel())
                .e_name(dto.getE_name())
                .e_idx(dto.getE_idx())
                .date(dto.getDate())
                .now_date(dto.getNow_date())
                .state(dto.getState())
                .pay(dto.getPay())
                .total(dto.getTotal())
                .review(dto.getReview())
                .count(dto.getCount())
                .e_price(dto.getE_price())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default ReserveDTO entityToDto(Reserve entity) {
        ReserveDTO dto = ReserveDTO.builder()
                .num(entity.getNum())
                .u_name(entity.getU_name())
                .u_id(entity.getU_id())
                .u_tel(entity.getU_tel())
                .e_name(entity.getE_name())
                .e_idx(entity.getE_idx())
                .date(entity.getDate())
                .now_date(entity.getNow_date())
                .state(entity.getState())
                .pay(entity.getPay())
                .total(entity.getTotal())
                .review(entity.getReview())
                .count(entity.getCount())
                .e_price(entity.getE_price())
                .build();
        return dto;
    }

    void modifyReserve(ReserveDTO dto);

    void modifyReviewYon(ReserveDTO dto);

    void modifyCancel(int num);

    List<Integer> getNumList();

    List<Reserve> selectReserve(String id);

}
