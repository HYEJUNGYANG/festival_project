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
public class NoticeDTO {

    private Long idx;
    private String title;
    private String content;
    private LocalDate date;
    private String filename;
    private String filepath;
}
