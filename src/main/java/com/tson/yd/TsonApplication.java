package com.tson.yd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tson.yd.dao")
public class TsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsonApplication.class, args);
    }

}
