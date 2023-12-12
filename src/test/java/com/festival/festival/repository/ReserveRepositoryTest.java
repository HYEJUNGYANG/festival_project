package com.festival.festival.repository;

import com.festival.festival.entity.Reserve;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
class ReserveRepositoryTest {

    @Autowired
    private ReserveRepository reserveRepository;

    @Test
    public void insertReserve(){

        IntStream.rangeClosed(1,50).forEach(i -> {
            Reserve reserve = Reserve.builder()
                    .num(111111 + i)
                    .u_name("예약자명" + i)
                    .u_id("ID"+i)
                    .u_tel("010-1111-1111")
                    .e_idx((long) i)
                    .e_name("체험명" + i)
                    .date(LocalDate.of(2023, 11, 11))
                    .now_date(LocalDateTime.now())
                    .state("예약완료")
                    .pay("무통장입금")
                    .total(100000+i)
                    .review('y')
                    .count(2)
                    .e_price(10000+i)
                    .build();
            reserveRepository.save(reserve);
        });
    }


}