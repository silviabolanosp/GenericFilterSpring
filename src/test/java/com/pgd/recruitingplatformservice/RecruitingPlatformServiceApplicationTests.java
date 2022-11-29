package com.pgd.recruitingplatformservice;

import com.pgd.recruitingplatformservice.controllers.RecruitingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RecruitingPlatformServiceApplicationTests {
    @Autowired
    private RecruitingController controller;
    @Test
    void contextLoads() {
        //need to be properly tested
        assertThat(controller).isNotNull();
    }

}
