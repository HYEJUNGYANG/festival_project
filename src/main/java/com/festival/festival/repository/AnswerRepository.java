package com.festival.festival.repository;

import com.festival.festival.entity.Answer;
import com.festival.festival.repository.custom.AnswerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>, AnswerRepositoryCustom {
}
