package com.yep;

import com.yep.mapper.NovelMapper;
import com.yep.pojo.Novel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class NovelWebsiteApplicationTests {

   @Autowired
   private NovelMapper novelMapper;
   @Test
   void contextLoads() {
   }
@Test
   public void testPassword(){
   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   String admin = encoder.encode("admin");
   System.out.println(admin);
}
@Test
   public  void testMapper(){
   List<Novel> list = novelMapper.getCurrentUserNovelCollections(1);
   list.stream()
           .forEach(System.out::println);
}
}
