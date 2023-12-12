package com.festival.festival.repository;

import com.festival.festival.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReview(){

        IntStream.rangeClosed(1,50).forEach(i -> {
            Review review = Review.builder()
                    .e_name("체험명"+i)
                    .e_idx((long)i)
                    .content("리뷰내용"+i)
                    .date(LocalDate.now())
                    .star(5)
                    .u_nick("닉네임"+i)
                    .build();
            reviewRepository.save(review);
        });
    }
}