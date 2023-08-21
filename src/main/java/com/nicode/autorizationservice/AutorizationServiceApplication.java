package com.nicode.autorizationservice;

import com.nicode.autorizationservice.handlerMethodArgumentResolvers.UserHandlerMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class AutorizationServiceApplication implements WebMvcConfigurer {
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserHandlerMethodArgumentResolver());
	}

	public static void main(String[] args) {
		SpringApplication.run(AutorizationServiceApplication.class, args);
	}

}
