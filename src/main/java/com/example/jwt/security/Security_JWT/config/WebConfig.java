package com.example.jwt.security.Security_JWT.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//	@Value("${file.upload-dir}")
//	private String uploadFile;
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/files/get/**")
//		.addResourceLocations("file:"+uploadFile+"/");
//	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOriginPatterns("*")
		.allowedMethods("GET","PUT","POST","DELETE","OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(3600);
		}

}