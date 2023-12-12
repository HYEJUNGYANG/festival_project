package com.festival.festival.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice")
@Data
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;


    @Column(length = 150, nullable = false)
    private String title;


    @Column(length = 1000, nullable = false)
    private String content;


    @CreatedDate
    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 250)
    private String filename;

    @Column(length = 250)
    private String filepath;
}
