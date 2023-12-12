package com.festival.festival.dto;

import com.festival.festival.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDTO {

    private Long idx;
    private String title;
    private String q_content;
    private LocalDate date;
    private String u_nick;
    private String u_id;
    private char yn;
    private char priv;
    private Answer answer;

}
