package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yep.mapper.AdminMapper;
import com.yep.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-24 19:20
 */
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private AdminMapper adminMapper;
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      if("".equals(username)){
         username="admin11";
      }
      QueryWrapper<Admin> wrapper = new QueryWrapper<>();
      wrapper.eq("username",username);
      Admin admin = adminMapper.selectOne(wrapper);
      if (admin==null) {
         throw new UsernameNotFoundException("当前管理员不存在！");
      }
      return admin;
   }
}
