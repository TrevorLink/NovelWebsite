package com.yep.controller;

import com.yep.pojo.RespBean;
import com.yep.pojo.User;
import com.yep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangSir
 * @date 2022-02-21 14:47
 */
@RestController
@RequestMapping("user")
public class UserController {
   @Autowired
   private UserService userService;
   @RequestMapping("register")
   public RespBean register(User user){
      return userService.register(user);
   }
}
