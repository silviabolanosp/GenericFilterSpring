package com.pgd.recruitingplatformservice.config;

import com.pgd.recruitingplatformservice.interceptor.RecruitingControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RecruitingControllerInterceptorConfig implements WebMvcConfigurer {

    /**
     * Dependency injection of RecruitingControllerInterceptor.
     */
    @Autowired
    private RecruitingControllerInterceptor recruitingControllerInterceptor;

    /**
     * Configuration to registry of recruitingControllerInterceptor.
     * @param registry object to register.
     */
    @Override
    public void addInterceptors(
            final InterceptorRegistry registry
    ) {
        registry.addInterceptor(recruitingControllerInterceptor);
    }
}
