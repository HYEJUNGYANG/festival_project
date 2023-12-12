package com.festival.festival.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Data
@Table(name = "exp")
public class Exp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 2, nullable = false)
    private String zone;//시,도

    @Column(length = 20, nullable = false)
    private String l_name;//구

    @Column(length = 1000, nullable = false)
    private String detail;

    @Column(length = 250, nullable = false)
    private String filename;

    @Column(length = 250, nullable = false)
    private String filepath;


    @Column(length = 200, nullable = false)
    private String place;//장소

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 1000, nullable = false)
    private String warning;

    @Column(length = 13, nullable = false)
    private String tel;//문의번호

    @Column(length = 100, nullable = false)
    private String link;//홈페이지

    @Column(length = 11, nullable = false)
    private String time;//이용시간

    @Column(nullable = false)
    private int price;

    @Column(length = 100, nullable = false)
    private String tag;

    @Column(nullable = false)
    private double latitude;//위도

    @Column(nullable = false)
    private double hardness;//경도

    @Column(nullable = false)
    private Long count;

}
