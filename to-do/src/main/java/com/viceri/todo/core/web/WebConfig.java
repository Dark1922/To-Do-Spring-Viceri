package com.viceri.todo.core.web;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	/*Liberado todos cors , caso queira fazer por url só adicionar no addMaping*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") 
		.allowedMethods("*"); 
		
	}

}
