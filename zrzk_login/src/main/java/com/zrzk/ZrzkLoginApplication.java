package com.zrzk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zrzk.mapper.LoginMapper")
public class ZrzkLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZrzkLoginApplication.class, args);
    }

}
