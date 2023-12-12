package com.festival.festival.repository;

import com.festival.festival.entity.Festival;
import com.festival.festival.repository.custom.FestivalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival,Long>, FestivalRepositoryCustom {
}
