package com.festival.festival.repository;

import com.festival.festival.entity.Reserve;
import com.festival.festival.repository.custom.ReserveRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer>, ReserveRepositoryCustom {
}
