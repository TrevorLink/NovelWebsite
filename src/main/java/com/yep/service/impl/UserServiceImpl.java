package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.UserMapper;
import com.yep.pojo.RespBean;
import com.yep.pojo.User;
import com.yep.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-21 14:48
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
   @Autowired
   private  UserMapper userMapper;
   //从数据库中获取用户信息
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      log.debug("当前登录的用户名：{}",username);
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.eq("username",username);
      User user = userMapper.selectOne(wrapper);
      if (user==null) throw  new UsernameNotFoundException("该用户不存在！");
      return user;
   }

   @Override
   public RespBean register(User user) {
      int insert = userMapper.insert(user);
      if(insert!=1){
         return  RespBean.error("服务器异常，注册失败！");
      }
      return RespBean.ok("注册成功！");
   }
}
