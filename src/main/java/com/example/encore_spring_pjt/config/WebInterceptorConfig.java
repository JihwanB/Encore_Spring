package com.example.encore_spring_pjt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.encore_spring_pjt.ctrl.user.interceptor.LoginInterceptor;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// login interceptor
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/index.hanwha");

		// board interceptor
		// registry.addInterceptor(new BoardInterceptor())
		//         .addPathPatterns("/board/**");

		// logout interceptor
		// registry.addInterceptor(new LogoutInterceptor())
		//         .addPathPatterns("/user/logout.hanwha");
	}
}
