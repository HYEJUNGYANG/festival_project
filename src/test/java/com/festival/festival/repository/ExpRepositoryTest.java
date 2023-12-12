package com.festival.festival.repository;

import com.festival.festival.entity.Exp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class ExpRepositoryTest {

    @Autowired
    private ExpRepository expRepository;

    @Test
    public void insertExp(){

        IntStream.rangeClosed(1,50).forEach(i ->{
            Exp exp = Exp.builder()
                    .name("축제"+i)
                    .zone("대전")
                    .l_name("서구 "+i)
                    .detail("어드민"+i)
                    .place("010-1111-1111")
                    .content("예시내용"+i)
                    .warning("유의사항예시"+i)
                    .tel("010-1111-1111")
                    .link("www.testpage"+i+".com")
                    .time("09:00-18:00")
                    .price(10000+i)
                    .tag("@@"+i+"@@")
                    .latitude(111.111111)
                    .hardness(22.2222222)
                    .build();
            expRepository.save(exp);
        });
    }

}