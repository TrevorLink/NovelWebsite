package com.yep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yep.pojo.Admin;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;

/**
 * @author HuangSir
 * @date 2022-02-21 14:42
 */
public interface AdminService extends IService<Admin> {
   /**
    * 管理员查询所有在审核状态的小说
    * @param currPage
    * @param size
    * @return
    */
   RespPage getNovelInProgressPage(Integer currPage, Integer size);

   RespBean novelSuccess(Integer id);

   RespBean deleteNovel(Integer id);
//
//   RespBean login(Admin admin);
}
