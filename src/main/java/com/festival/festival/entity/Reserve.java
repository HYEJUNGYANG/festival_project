package com.festival.festival.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Data
@Table(name = "reserve")
public class Reserve {

    @Id
    @Column(name="reserve_num")
    private int num; //랜덤8자리 숫자

    @Column(length = 10, nullable = false)
    private String u_name;//예약자명

    @Column(length = 30, nullable = false)
    private String u_id;//예약자id

    @Column(length = 13, nullable = false)
    private String u_tel;//번호(유저)

    @Column(length = 50, nullable = false)
    private String e_name;//체험명

    @Column(nullable = false)
    private Long e_idx;// 체험 idx

    @Column(nullable = false)
    private LocalDate date;//체험하는날짜

    @CreatedDate
    @Column(name = "now_date",updatable = false, nullable = false)
    private LocalDateTime now_date;

    @Column(length = 4, nullable = false)
    private String state;//예약상태

    @Column(length = 8, nullable = false)
    private String pay;//결제수단

    @Column(nullable = false)
    private int total;//결제금액

    @Column(length = 1, nullable = false)
    private char review;//리뷰 y or n

    @Column(nullable = false)
    private int count;//인원수

    @Column(nullable = false)
    private int e_price;//이용요금(체험)

}
