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
@ToString(exclude = "question")
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 1000, nullable = false)
    private String a_content;//답변내용

    @CreatedDate
    @Column
    private LocalDate date;

    @OneToOne(mappedBy = "answer") // question table과 연결하는 부분
    private Question question;

}
