//package com.yep.config;
//
//import com.yep.handler.MyAuthenticationEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @author HuangSir
// * @date 2022-02-23 14:58
// */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////   @Autowired
////   private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
//   /**
//    * 向容器中注入密码匹配器
//    * @return
//    */
//   @Bean
//   public PasswordEncoder passwordEncoder(){
//      return new BCryptPasswordEncoder();
//   }
//
//   /**
//    * 向容器中注入AuthenticationManager为了让service(controller)承接我们自定义的认证策略进行下去
//    * @return
//    * @throws Exception
//    */
//   @Bean
//   @Override
//   public AuthenticationManager authenticationManagerBean() throws Exception {
//      return super.authenticationManagerBean();
//   }
//
//
//   /**
//    * HTTP的自定义配置
//    * @param http
//    * @throws Exception
//    */
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//      http
//              //关闭csrf
//              .csrf().disable()
//              //不通过Session获取SecurityContext
//              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//              .and()
//              .authorizeRequests()
//              // 对于登录接口 允许匿名访问
//              .antMatchers("/user/login","/user/register","/admin/login","/admin/register").anonymous()
//              // 除上面外的所有请求全部需要鉴权认证
//              .anyRequest().authenticated();
//      //Security的跨域配置
//      http.cors();
//   }
//}
//
