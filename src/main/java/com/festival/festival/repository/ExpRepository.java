package com.festival.festival.repository;

import com.festival.festival.entity.Exp;
import com.festival.festival.repository.custom.ExpRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpRepository extends JpaRepository<Exp,Long>, ExpRepositoryCustom {
}
