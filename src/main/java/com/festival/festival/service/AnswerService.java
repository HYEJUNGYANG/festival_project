package com.festival.festival.service;

import com.festival.festival.dto.AnswerDTO;
import com.festival.festival.entity.Answer;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    AnswerDTO read(Answer answer);

    Answer insertAnswer(AnswerDTO a_dto);

    void modifyAnswer(AnswerDTO a_dto);

    default Answer dtoToEntity(AnswerDTO dto) {
        Answer entity = Answer.builder()
                .idx(dto.getIdx())
                .a_content(dto.getA_content())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default AnswerDTO entityToDto(Answer entity) {
        AnswerDTO dto = AnswerDTO.builder()
                .idx(entity.getIdx())
                .a_content(entity.getA_content())
                .build();
        return dto;
    }
}
