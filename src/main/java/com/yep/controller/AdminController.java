package com.yep.controller;

import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
   @ApiOperation("小说审核通过")
   public RespBean novelSuccess(@ApiParam(required = true) Integer id){
      return  adminService.novelSuccess(id);
   }
   @ApiOperation("小说审核不通过")
   @DeleteMapping("/")
   public RespBean deleteNovel(@ApiParam(required = true) Integer id){
      return  adminService.deleteNovel(id);
   }
}
