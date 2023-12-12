package com.festival.festival.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50, nullable = false)
    private String e_name;//체험명

    @Column(length = 150, nullable = false)
    private String content;//리뷰내용

    @Column(nullable = false)
    private Long e_idx;//체험 idx;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate date;//리뷰 작성 날짜

    @Column(nullable = false)
    private int star;//별점

    @Column(length = 10, nullable = false)
    private String u_nick;

    @Column(length = 30, nullable = false)
    private String u_id; // 유저 아이디!! 가져올 값이 필요함
}
