package com.festival.festival.repository;

import com.festival.festival.entity.Festival;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class FestivalRepositoryTest {

    @Autowired
    private FestivalRepository festivalRepository;

    @Test
    public void insertFestival(){

        IntStream.rangeClosed(1,50).forEach(i ->{
            Festival festival = Festival.builder()
                    .name("축제"+i)
                    .zone("대전")
                    .l_name("서구"+i)
                    .detail("어드민"+i)
//                    .img("M")
                    .place("010-1111-1111")
                    .tel("010-1111-1111")
                    .link("www.testpage"+i+".com")
                    .time("09:00-18:00")
                    .filename("filename" + i)
                    .filepath("filepath" + i)
                    .start(LocalDate.of(2023,11,11))
                    .end(LocalDate.of(2023,11,18))
                    .latitude(111.111111)
                    .hardness(22.2222222)
                    .price(10000+i)
                    .tag("@@"+i+"@@")
                    .build();
            festivalRepository.save(festival);
        });
    }
}