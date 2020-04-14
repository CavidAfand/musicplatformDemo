package com.musicplatform.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
        registry.addResourceHandler("/postUploads/**").addResourceLocations("file:postUploads/");
        registry.addResourceHandler("/musicUploads/**").addResourceLocations("file:musicUploads/");
    }
}
