package com.zrzk.rms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zrzk.rms.mapper")
public class RmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmsApplication.class, args);
    }

}
