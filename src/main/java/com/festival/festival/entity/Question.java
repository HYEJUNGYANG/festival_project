package com.festival.festival.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String q_content;


    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 10, nullable = false)
    private String u_nick;

    @Column(length = 30, nullable = false)
    private String u_id;

    @Column(length = 1, nullable = false)
    private char yn;

    @Column(length = 1, nullable = false)
    private char priv;

    // join
    @OneToOne
    @JoinColumn(name = "answer") // unique key
    private Answer answer;

}
