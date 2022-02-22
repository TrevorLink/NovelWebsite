package com.yep.controller;

import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HuangSir
 * @date 2022-02-21 14:45
 */
@RestController
@RequestMapping("admin")
public class AdminController {
   @Autowired
   private AdminService adminService;
   @GetMapping("/")
   public RespPage getNovelInProgressPage(@RequestParam(defaultValue = "1") Integer currPage,
                                          @RequestParam(defaultValue = "10")Integer size){
      return adminService.getNovelInProgressPage(currPage,size);
   }
   @PutMapping("/success")
   public RespBean novelSuccess(Integer id){
      return  adminService.novelSuccess(id);
   }
   @DeleteMapping("/")
   public RespBean deleteNovel(Integer id){
      return  adminService.deleteNovel(id);
   }
}
