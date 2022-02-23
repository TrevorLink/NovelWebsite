package com.yep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yep.mapper")
public class NovelWebsiteApplication {

   public static void main(String[] args) {
      SpringApplication.run(NovelWebsiteApplication.class, args);
   }

}
