package com.yep.controller;

import com.yep.pojo.Admin;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author HuangSir
 * @date 2022-02-21 14:45
 */
@RestController
@Api("管理员模块接口功能")
@RequestMapping("admin")
public class AdminController {
   @Autowired
   private AdminService adminService;
   @GetMapping("/")
   @ApiOperation(value = "查看审核列表",notes = "查看用户上传的但是正在处于审核状态的小说列表")
   public RespPage getNovelInProgressPage(@ApiParam(required = false,value = "当前页码（默认1）") @RequestParam(defaultValue = "1") Integer currPage,
                                          @RequestParam(defaultValue = "10",value = "每页的展示个数（默认10）")Integer size){
      return adminService.getNovelInProgressPage(currPage,size);
   }
   @PutMapping("/success")
   @ApiOperation(value = "小说审核通过",notes = "针对审核通过的小说修改状态为上架状态，之后可以在主页中查到")
   public RespBean novelSuccess(@ApiParam(required = true) Integer id){
      return  adminService.novelSuccess(id);
   }
   @ApiOperation(value = "小说审核不通过",notes = "针对审核不通过的小说，删除记录")
   @DeleteMapping("/")
   public RespBean deleteNovel(@ApiParam(required = true) Integer id){
      return  adminService.deleteNovel(id);
   }
   @ApiOperation(value = "管理员登录",notes = "管理员账号登录，注意登录的接口是/admin/doLogin")
   @PostMapping("/login")
//   @ApiImplicitParams({
//           @ApiImplicitParam(name = "username",value = "管理员用户名",required = true),
//           @ApiImplicitParam(name = "password",value = "管理员密码",required = true)
//   })
   public RespBean login(Admin admin){
//      return adminService.login(admin);
      return  RespBean.ok("登陆成功！");
   }
   @ApiOperation(value = "用户注销",notes = "管理员注销接口")
   @PostMapping("/logout")
   public RespBean logout(){
      return  RespBean.ok("管理员注销成功！");
   }
}
