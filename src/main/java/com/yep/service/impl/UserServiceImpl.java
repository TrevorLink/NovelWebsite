package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.UserMapper;
import com.yep.pojo.RespBean;
import com.yep.pojo.User;
import com.yep.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-21 14:48
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   @Autowired
   private  UserMapper userMapper;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public RespBean register(User user) {
      //用户注册的时候先加密再存到数据库中
      log.debug("加密前即将注册的用户数据：{}",user);
      String encode = passwordEncoder.encode(user.getPassword());
      user.setPassword(encode);
      log.debug("加密后即将注册的用户数据：{}",user);
      int insert = userMapper.insert(user);
      if(insert!=1){
         return  RespBean.error("服务器异常，注册失败！");
      }
      return RespBean.ok("注册成功！");
   }
//
//   @Override
//   public RespBean login(User user) {
//      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//      if (authenticate==null){
//         throw  new RuntimeException("登陆失败！");
//      }
//      return RespBean.ok("登陆成功！");
//   }
}
