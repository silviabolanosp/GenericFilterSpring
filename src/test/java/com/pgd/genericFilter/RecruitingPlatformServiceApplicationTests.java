package com.pgd.genericFilter;

import com.pgd.genericFilter.controllers.RecruitingController;
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
