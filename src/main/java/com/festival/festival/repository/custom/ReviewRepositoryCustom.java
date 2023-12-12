package com.festival.festival.repository.custom;

import com.festival.festival.dto.ReviewDTO;
import com.festival.festival.entity.Review;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> getList(Long idx);

    List<Review> findListById(String id);

    void InsertReview(ReviewDTO dto);

    void updateReview(ReviewDTO dto);
}
