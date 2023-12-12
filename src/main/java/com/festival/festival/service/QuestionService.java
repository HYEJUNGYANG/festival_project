package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    QuestionDTO read(Long idx);

    Long insertAnswer(QuestionDTO dto);

    void insertQuestion(QuestionDTO dto);

    void updateQuestion(QuestionDTO dto);

    void deleteQuestion(Long idx);

    String findUserid();
    Long count();

    PageResultDTO<QuestionDTO, Question> getList(PageRequestDTO requestDTO);


    default Question dtoToEntity(QuestionDTO dto) {
        Question entity = Question.builder()
                .idx(dto.getIdx())
                .title(dto.getTitle())
                .q_content(dto.getQ_content())
                .date(dto.getDate())
                .u_nick(dto.getU_nick())
                .u_id(dto.getU_id())
                .yn(dto.getYn())
                .priv(dto.getPriv())
                .answer(dto.getAnswer())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default QuestionDTO entityToDto(Question entity) {
        QuestionDTO dto = QuestionDTO.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .q_content(entity.getQ_content())
                .date(entity.getDate())
                .u_nick(entity.getU_nick())
                .u_id(entity.getU_id())
                .yn(entity.getYn())
                .priv(entity.getPriv())
                .answer(entity.getAnswer())
                .build();
        return dto;
    }

    List<Question> getTop3List(char yn);


}
