package com.zouwu.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan(basePackages = "com.zouwu.trade")
public class SchoolUsedTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolUsedTradeApplication.class, args);
    }
}
