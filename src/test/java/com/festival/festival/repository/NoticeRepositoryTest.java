package com.festival.festival.repository;

import com.festival.festival.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void insertNotice(){

        IntStream.rangeClosed(1,80).forEach(i ->{
            Notice notice = Notice.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .filename("")
                    .filepath("")
                    .date(LocalDate.of(2023,11,11))
                    .build();
            noticeRepository.save(notice);
        });
    }

}