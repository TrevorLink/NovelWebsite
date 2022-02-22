package com.yep.config;

import com.yep.handler.MyAuthenticationEntryPoint;
import com.yep.handler.MyAuthenticationFailureHandler;
import com.yep.handler.MyLogoutSuccessHandler;
import com.yep.handler.UserAuthenticationSuccessHandler;
import com.yep.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author HuangSir
 * @date 2022-02-21 15:33
 */
@EnableWebSecurity
public class MultiHttpSecurityConfig {
   /**
    *    配置密码加密解密的规则
    */
   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }
   @Configuration
   @Order(1)
   public static class UserSecurityConfiguration extends WebSecurityConfigurerAdapter{
      @Autowired
      private UserServiceImpl userService;
      @Autowired
      private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
      @Autowired
      private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
      @Autowired
      private MyLogoutSuccessHandler myLogoutSuccessHandler;
      @Autowired
      private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
      /**
       *  设置数据库的校验规则
       */
      @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userService);
      }

      //忽略"/login","/verifyCode"请求，该请求不需要进入Security的拦截器
      @Override
      public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/user/login","/file","/user/register","/user/checkUsername");
      }

      @Override
      protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
                 //任何请求都要认证
                 .anyRequest().authenticated()
                 .and()
                 .formLogin()
                 .usernameParameter("username")
                 .passwordParameter("password")
//                 .loginPage("/login")
                 .loginProcessingUrl("/user/doLogin")
                 //登录成功处理器
                 .successHandler(userAuthenticationSuccessHandler)
                 //登录失败处理器
                 .failureHandler(myAuthenticationFailureHandler)
                 .permitAll()
                 .and()
                 //配置登出的逻辑
                 .logout()
                 .logoutUrl("/logout")
                 //登出成功处理器
                 .logoutSuccessHandler(myLogoutSuccessHandler)
                 .permitAll()
                 .and()
                 .csrf().disable()//关闭csrf防御方便调试
                 //没有认证时，在这里处理结果，不进行重定向到login页
                 .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
      }
   }
}
