package com.aso.examplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class AsoExamPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsoExamPlatformApplication.class, args);
    }

}
