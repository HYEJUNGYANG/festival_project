package com.festival.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveDTO {

    private int num;
    private String u_name;
    private String u_id;
    private String u_tel;
    private String e_name;
    private Long e_idx;
    private LocalDate date;
    private LocalDateTime now_date;
    private String state;
    private String pay;
    private int total;
    private char review;
    private int count;
    private int e_price;

}
