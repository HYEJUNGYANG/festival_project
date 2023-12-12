package com.festival.festival;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ComponentScan(nameGenerator = CustomBeanNameGenerator.class)
@EnableJpaAuditing
@SpringBootApplication
public class FestivalApplication {
	public static void main(String[] args) {
		SpringApplication.run(FestivalApplication.class, args);
	}

}
