package com.zrzk_mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zrzk_mybatisplus.mapper")
public class ZrzkMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZrzkMybatisPlusApplication.class, args);
    }

}
