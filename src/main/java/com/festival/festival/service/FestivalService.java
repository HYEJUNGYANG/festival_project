package com.festival.festival.service;

import com.festival.festival.dto.FestivalDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.entity.Festival;
import com.festival.festival.dto.PageResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public interface FestivalService {


    Long join(FestivalDTO dto, MultipartFile file) throws IOException;
    FestivalDTO read(Long idx);

    Long count();

    List<Festival> getList(String zone);
    PageResultDTO<FestivalDTO, Festival> getList(PageRequestDTO requestDTO);

    List<Festival> getListByIdxs(int[] idxList);

    Long update(Long idx, FestivalDTO dto, MultipartFile file) throws IOException;

    default Festival dtoToEntity(FestivalDTO dto) {
        Festival entity = Festival.builder()
                .idx(dto.getIdx())
                .name(dto.getName())
                .zone(dto.getZone())
                .l_name(dto.getL_name())
                .detail(dto.getDetail())
                .filename(dto.getFilename())
                .filepath(dto.getFilepath())
                .place(dto.getPlace())
                .tel(dto.getTel())
                .link(dto.getLink())
                .time(dto.getTime())
                .start(dto.getStart())
                .end(dto.getEnd())
                .latitude(dto.getLatitude())
                .hardness(dto.getHardness())
                .price(dto.getPrice())
                .tag(dto.getTag())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default FestivalDTO entityToDto(Festival entity) {
        FestivalDTO dto = FestivalDTO.builder()
                .idx(entity.getIdx())
                .name(entity.getName())
                .zone(entity.getZone())
                .l_name(entity.getL_name())
                .detail(entity.getDetail())
                .filename(entity.getFilename())
                .filepath(entity.getFilepath())
                .place(entity.getPlace())
                .tel(entity.getTel())
                .link(entity.getLink())
                .time(entity.getTime())
                .start(entity.getStart())
                .end(entity.getEnd())
                .latitude(entity.getLatitude())
                .hardness(entity.getHardness())
                .price(entity.getPrice())
                .tag(entity.getTag())
                .build();
        return dto;
    }

    void insertFestival(Festival festival);

    void deleteFestival(Long idx);

    void modifyFestival(FestivalDTO dto);

    //최신 게시글 3개 꺼내오기
    public List<Festival> getTop3List();
}
