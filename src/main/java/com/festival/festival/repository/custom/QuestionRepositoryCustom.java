package com.festival.festival.repository.custom;

import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.entity.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    void InsertQuestion(QuestionDTO dto);

    void updateQuestion(QuestionDTO dto);

    List<Question> findTop3ByOrderByIdDesc(char yn);// idx를 기준, desc로 3개의 데이터를 꺼내옴
}

