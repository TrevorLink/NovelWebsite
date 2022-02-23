package com.yep.controller;

import com.yep.pojo.RespBean;
import com.yep.pojo.User;
import com.yep.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangSir
 * @date 2022-02-21 14:47
 */
@RestController
@Api(value = "用户模块",description = "用户模块相关的功能，登录是在/user/doLogin这个接口")
@RequestMapping("user")
public class UserController {
   @Autowired
   private UserService userService;
   @ApiOperation(value = "用户注册",notes="提交用户注册表单进行注册")
   @PostMapping("register")
   public RespBean register(User user){
      return userService.register(user);
   }
}
