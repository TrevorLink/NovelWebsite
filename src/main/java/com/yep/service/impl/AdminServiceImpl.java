package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.AdminMapper;
import com.yep.mapper.NovelMapper;
import com.yep.pojo.*;
import com.yep.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:42
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService, UserDetailsService {
   @Autowired
   private  AdminMapper adminMapper;
   @Autowired
   private NovelMapper novelMapper;
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      QueryWrapper<Admin> wrapper = new QueryWrapper<>();
      wrapper.eq("username",username);
      Admin admin = adminMapper.selectOne(wrapper);
      if (admin==null) throw new UsernameNotFoundException("当前管理员不存在！");
      return admin;
   }

   @Override
   public RespPage getNovelInProgressPage(Integer currPage, Integer size) {
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.eq("status", NovelStatus.IN_PROGRESS);
      Page<Novel> page = new Page<>(currPage, size);
      Page<Novel> selectPage = novelMapper.selectPage(page, wrapper);
      Long count = novelMapper.selectCount(wrapper);
      List<Novel> novels = selectPage.getRecords();
      ArrayList<NovelPage> list = new ArrayList<>();
      for(Novel novel:novels){
         list.add(new NovelPage(novel.getId(),novel.getImageUrl(),novel.getName()));
      }
      return new RespPage(count,list);
   }

   @Override
   public RespBean novelSuccess(Integer id) {
      Novel novel = new Novel();
      novel.setId(id);
      novel.setStatus(NovelStatus.ON_LIST);
      int update = novelMapper.updateById(novel);
      if (update!=1){
         return RespBean.error("审核成功提交失败！");
      }
      return RespBean.ok("提交审核成功！");
   }

   @Override
   public RespBean deleteNovel(Integer id) {
      int delete = novelMapper.deleteById(id);
      if(delete!=1){
         return  RespBean.error("删除失败！");
      }
      return RespBean.ok("删除成功！");
   }
}
