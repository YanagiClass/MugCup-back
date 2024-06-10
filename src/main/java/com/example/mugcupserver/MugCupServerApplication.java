package com.example.mugcupserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MugCupServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MugCupServerApplication.class, args);
    }

}
