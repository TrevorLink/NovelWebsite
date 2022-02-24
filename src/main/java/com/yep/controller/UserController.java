package com.yep.controller;

import com.yep.pojo.RespBean;
import com.yep.pojo.User;
import com.yep.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author HuangSir
 * @date 2022-02-21 14:47
 */
@RestController
@Api(value = "用户模块",description = "用户模块相关的功能，登录是在/user/doLogin这个接口")
@Slf4j
@RequestMapping("user")
public class UserController {
   @Autowired
   private UserService userService;
   @ApiOperation(value = "用户注册",notes="提交用户注册表单进行注册")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username",value = "用户名",required = true),
           @ApiImplicitParam(name = "password",value = "密码",required = true)
   })
   @PostMapping("/register")
   public RespBean register(@ApiIgnore User user){
      return userService.register(user);
   }
   @ApiOperation(value = "用户登录",notes = "提交登录表单来进行登录，注意登录接口是/user/doLogin")
   @PostMapping("/login")
//   @ApiImplicitParams({
//           @ApiImplicitParam(name = "username",value = "用户名",required = true),
//           @ApiImplicitParam(name = "password",value = "密码",required = true)
//   })
   public RespBean login(User user){
//      return  userService.login(user);
      return  RespBean.ok("登陆成功！");
   }
   @ApiOperation(value = "用户注销",notes = "注销接口")
   @PostMapping("/logout")
   public RespBean logout(){
      return  RespBean.ok("注销成功！");
   }
}
