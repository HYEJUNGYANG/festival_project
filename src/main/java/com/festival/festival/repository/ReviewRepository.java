package com.festival.festival.repository;

import com.festival.festival.entity.Review;
import com.festival.festival.repository.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
}
