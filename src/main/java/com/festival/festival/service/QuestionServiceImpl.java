package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.entity.Question;
import com.festival.festival.repository.AnswerRepository;
import com.festival.festival.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    @Override
    public QuestionDTO read(Long idx) {
        Optional<Question> result = questionRepository.findById(idx);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public Long insertAnswer(QuestionDTO dto) {
        Question entity = dtoToEntity(dto);

        questionRepository.save(entity);

        return entity.getIdx();
    }

    @Override
    public void insertQuestion(QuestionDTO dto) {
        questionRepository.InsertQuestion(dto);
    }

    @Override
    public void updateQuestion(QuestionDTO dto) {
        questionRepository.updateQuestion(dto);
    }

    @Override
    public void deleteQuestion(Long idx) {
        questionRepository.deleteById(idx);
    }

    @Override
    public String findUserid() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy()
                .getContext().getAuthentication();

        String user_id = null;

        //값이 null이 아닐때 && 로그인 했을 경우에만
        if(authentication != null && authentication.isAuthenticated()) {
            // 불러온 id를 string 변수에 저장
            user_id = authentication.getName();
            // model로 dto와는 따로 보내기(u_id와 비교용)
            return user_id;
        }
        else{

            return user_id;
        }
    }

    @Override
    public Long count() {
        Long questionCount = questionRepository.count();

        return questionCount;
    }

    @Override
    public PageResultDTO<QuestionDTO, Question> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("idx").descending());
        Page<Question> result = questionRepository.findAll( pageable);
        Function<Question, QuestionDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn );
    }

    @Override // 답변 대기중 게시물 3개 가져오기
    public List<Question> getTop3List(char yn) {
        List<Question> dto = null;

        dto = questionRepository.findTop3ByOrderByIdDesc(yn);

        return dto;
    }
}
