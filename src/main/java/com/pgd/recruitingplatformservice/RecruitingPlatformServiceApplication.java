package com.pgd.recruitingplatformservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class RecruitingPlatformServiceApplication {

    /**
     * Static method to start the spring boot application.
     *
     * @param args array of {@link String} of values pass to run the application
     */
    public static void main(final String[] args) {
        SpringApplication.run(RecruitingPlatformServiceApplication.class, args);
    }

}
