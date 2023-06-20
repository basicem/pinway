package com.example.notificationservice;


import interceptor.SystemEventHandlerInterceptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@OpenAPIDefinition
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

//    @Bean
//    public SystemEventHandlerInterceptor myCustomHandlerInterceptor() {
//        return new SystemEventHandlerInterceptor();
//    }
//    @Bean
//    public WebMvcConfigurer adapter() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(myCustomHandlerInterceptor());
//            }
//        };
//    }
}
