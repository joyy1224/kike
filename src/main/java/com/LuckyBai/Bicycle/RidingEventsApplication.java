package com.LuckyBai.Bicycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class RidingEventsApplication {
    public static void main(String[] args) {

        SpringApplication.run(RidingEventsApplication.class, args);
        log.info("项目创建成功*****************************");
    }
}
