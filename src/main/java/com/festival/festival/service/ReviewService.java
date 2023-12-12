package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.entity.Review;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.ReviewDTO;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    ReviewDTO read(Long idx);

    PageResultDTO<ReviewDTO, Review> getList(PageRequestDTO requestDTO);

    List<Review> getList(Long idx);

    List<Review> selectReview(String id);

    default Review dtoToEntity(ReviewDTO dto) {
        Review entity = Review.builder()
                .idx(dto.getIdx())
                .e_name(dto.getE_name())
                .content(dto.getContent())
                .e_idx(dto.getE_idx())
                .star(dto.getStar())
                .u_id(dto.getU_id())
                .u_nick(dto.getU_nick())
                .date(dto.getDate())
                .build();
        return entity;
    }

    //entity -> dto 변환
    default ReviewDTO entityToDto(Review entity) {
        ReviewDTO dto = ReviewDTO.builder()
                .idx(entity.getIdx())
                .e_name(entity.getE_name())
                .content(entity.getContent())
                .e_idx(entity.getE_idx())
                .star(entity.getStar())
                .u_id(entity.getU_id())
                .u_nick(entity.getU_nick())
                .date(entity.getDate())
                .build();
        return dto;
    }

    void deleteReview(Long idx);

    void insertReview(ReviewDTO dto);

    void updateReview(ReviewDTO dto);
}
