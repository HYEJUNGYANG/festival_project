package com.festival.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FestivalDTO {

    private Long idx;
    private String name;
    private String zone;
    private String l_name;
    private String detail;
    private String filename;
    private String filepath;
    private String place;
    private String tel;
    private String link;
    private String time;
    private LocalDate start;
    private LocalDate end;
    private int price;
    private String tag;
    private double latitude;
    private double hardness;


}
