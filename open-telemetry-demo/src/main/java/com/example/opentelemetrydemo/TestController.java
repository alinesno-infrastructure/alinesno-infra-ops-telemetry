package com.example.opentelemetrydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class) ;

    @GetMapping("/testSpan")
    public String testSpan(String name) throws InterruptedException {

        // 创建一个Random对象
        Random random = new Random();

        // 生成1到10之间的随机数
        int randomNumber = random.nextInt(10) + 1;

        log.debug("username = " + name);
        log.info("username = " + name);
        log.error("username = " + name);

        Thread.sleep(randomNumber * 100);

        if(randomNumber % 5 == 0){
            int i = randomNumber/10  ;
        }

        return "helloworld " + name ;
    }

}
