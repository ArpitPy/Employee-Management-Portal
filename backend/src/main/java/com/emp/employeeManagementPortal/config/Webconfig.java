package com.emp.employeeManagementPortal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class Webconfig implements WebMvcConfigurer {

    @Value("${path.frontEnd}")
    private String frontendDir;
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
                .addResourceLocations("file:"+frontendDir)
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
