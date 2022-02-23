package com.yep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HuangSir
 * @date 2022-02-22 23:01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
   //规定扫描哪些包去生成Swagger文档
   @Bean
   public Docket createRestApi(){
      return  new Docket(DocumentationType.SWAGGER_2)//文档类型
              .apiInfo(apiInfo())
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.yep.controller"))
              .paths(PathSelectors.any())
              .build();
   }
   private ApiInfo apiInfo(){
      return  new ApiInfoBuilder()
              .title("小说网站")
              .description("西二合作轮")
              .contact(new Contact("小黄鸭","http://localhost:8080/doc.html","2845964844@qq.com"))
              .version("1.0")
              .build();
   }
}
