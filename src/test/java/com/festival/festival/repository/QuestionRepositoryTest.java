package com.festival.festival.repository;

import com.festival.festival.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void insertQuestion(){

        IntStream.rangeClosed(1,50).forEach(i ->{
            Question question = Question.builder()
                    .title("질문제목"+i)
                    .q_content("질문내용 "+i)
                    .date(LocalDate.of(2000,11,11))
                    .u_nick("닉네임"+i)
                    .u_id("ID"+i)
                    .yn('y')
                    .priv('n')
                    .build();
            questionRepository.save(question);
        });
    }
}