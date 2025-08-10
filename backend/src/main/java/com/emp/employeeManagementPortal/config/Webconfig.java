package com.emp.employeeManagementPortal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    /**
     * Maps the URLs to HTML without .html in browser
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("forward:/Home.html");
        registry.addViewController("/login").setViewName("forward:/Login.html");
        registry.addViewController("/register").setViewName("forward:/Register.html");
        registry.addViewController("/dashboard").setViewName("forward:/Dashboard.html");
    }

    /**
     * Provides all the Frontend files from the External Frontend Folder
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**", "/css/**", "/js/**", "/images/**")
                .addResourceLocations("file:D:/Projects/Employee-Management-Portal/frontend/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
