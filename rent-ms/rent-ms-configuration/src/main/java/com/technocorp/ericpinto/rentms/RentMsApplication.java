package com.technocorp.ericpinto.rentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RentMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentMsApplication.class, args);
    }


}