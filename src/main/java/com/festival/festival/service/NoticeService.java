package com.festival.festival.service;

import com.festival.festival.dto.NoticeDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.entity.Notice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface NoticeService {
    NoticeDTO read(Long idx);
    Long join(NoticeDTO dto, MultipartFile file) throws IOException;

    PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO requestDTO);

    Long count();

    List<Object> getPrevIdxAndTitle(Long idx);
    List<Object> getNextIdxAndTitle(Long idx);

    default Notice dtoToEntity(NoticeDTO dto) {
        Notice entity = Notice.builder()
                .idx(dto.getIdx())
                .title(dto.getTitle())
                .content(dto.getContent())
                .filename(dto.getFilename())
                .filepath(dto.getFilepath())
                .date(dto.getDate())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default NoticeDTO entityToDto(Notice entity) {
        NoticeDTO dto = NoticeDTO.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .content(entity.getContent())
                .filename(entity.getFilename())
                .filepath(entity.getFilepath())
                .date(entity.getDate())
                .build();
        return dto;
    }

    void insertNotice(Notice notice);

    void deleteNotice(Long idx);

    void modifyNotice(NoticeDTO dto);

    Long update(Long idx, NoticeDTO dto, MultipartFile file) throws IOException;

}
