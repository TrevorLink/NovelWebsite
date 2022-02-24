package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yep.mapper.UserMapper;
import com.yep.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-23 21:28
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private UserMapper userMapper;
   //从数据库中获取用户信息
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      log.debug("当前登录的用户名：{},{}",username,username.length());
      if ("".equals(username)){
         username="user";
      }
      log.debug("处理后的用户名：{}",username);
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.eq("username",username);
      User user = userMapper.selectOne(wrapper);
      if (user==null) {
         throw  new UsernameNotFoundException("用户名或密码错误");
      }
      return user;
   }
}
