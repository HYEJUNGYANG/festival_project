package com.festival.festival.service;

import com.festival.festival.dto.AnswerDTO;
import com.festival.festival.entity.Answer;
import com.festival.festival.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public AnswerDTO read(Answer answer) {
        Optional<Answer> result = answerRepository.findById(answer.getIdx());

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public Answer insertAnswer(AnswerDTO a_dto) {
        Answer entity = dtoToEntity(a_dto);

        answerRepository.save(entity);

        return entity;
    }

    @Override
    public void modifyAnswer(AnswerDTO a_dto) {
        answerRepository.modifyById(a_dto);
    }
}
