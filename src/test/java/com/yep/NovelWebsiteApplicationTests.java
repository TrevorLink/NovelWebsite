package com.yep;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class NovelWebsiteApplicationTests {

   @Test
   void contextLoads() {
   }
@Test
   public void testPassword(){
   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   String admin = encoder.encode("admin");
   System.out.println(admin);
}
}
