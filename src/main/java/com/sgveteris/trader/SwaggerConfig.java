package com.sgveteris.trader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {                                    
    @Bean
    public Docket api() { 
    	ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
    	responseMessages.add(
    			new ResponseMessageBuilder()   
		        .code(400)
		        .message("message detail in errorMessage")
		        .build());
    	responseMessages.add(
    			new ResponseMessageBuilder()   
		        .code(500)
		        .message("message detail in errorMessage")
		        .build());
    	responseMessages.add(
    		new ResponseMessageBuilder() 
    		    .code(200)
    		    .build());

        return new Docket(DocumentationType.SWAGGER_2)
          .useDefaultResponseMessages(false)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.sgveteris"))              
          .paths(PathSelectors.any())
          .build()
          .apiInfo(getApiInfo())
          .globalResponseMessage(RequestMethod.POST, responseMessages);
    }

    private static ApiInfo getApiInfo() {
        return new ApiInfo(
          "Rest API", 
          "API Description", 
          "v0.1", 
          "Terms of service", 
          new springfox.documentation.service.Contact(
        		  "SG Veteris", 
        		  "https://www.sgveteris.com",
        		  "info@sgveteris.com"), 
          "License of API",
          "API license URL",
          new ArrayList<>());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}