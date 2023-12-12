package com.festival.festival.repository;

import com.festival.festival.entity.Question;
import com.festival.festival.repository.custom.QuestionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, QuestionRepositoryCustom {
}
