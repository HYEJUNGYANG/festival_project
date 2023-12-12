package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.entity.Review;
import com.festival.festival.repository.ReviewRepository;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.ReviewDTO;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDTO read(Long idx) {
        Optional<Review> result = reviewRepository.findById(idx);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public PageResultDTO<ReviewDTO, Review> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("idx").descending());
        Page<Review> result = reviewRepository.findAll(pageable);
        Function<Review, ReviewDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public List<Review> getList(Long idx) {
        List<Review> dto = reviewRepository.getList(idx);
        return dto;
    }

    @Override
    public List<Review> selectReview(String id) {
        return reviewRepository.findListById(id);
    }

    public void deleteReview(Long idx) {
        reviewRepository.deleteById(idx);
    }

    @Override
    public void insertReview(ReviewDTO dto) {
        reviewRepository.InsertReview(dto);
    }

    @Override
    public void updateReview(ReviewDTO dto) {
        reviewRepository.updateReview(dto);
    }

}