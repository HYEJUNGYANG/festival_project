package com.festival.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpDTO {

    private Long idx;
    private String name;
    private String zone;
    private String l_name;
    private String detail;
    private String filename;
    private String filepath;
    private String place;
    private String content;
    private String warning;
    private String tel;
    private String link;
    private String time;
    private int price;
    private String tag;
    private double latitude;
    private double hardness;
    private Long count;

}
