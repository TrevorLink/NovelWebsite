package com.yep.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yep.pojo.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
      response.setContentType("application/json;charset=utf-8");
      PrintWriter out = response.getWriter();
      RespBean error = RespBean.error("登录失败!");
      if (exception instanceof LockedException) {
         error.setMessage("账户已锁定，请联系管理员！");
      } else if (exception instanceof CredentialsExpiredException) {
         error.setMessage("密码已过期，请联系管理员！");
      } else if (exception instanceof AccountExpiredException) {
         error.setMessage("账户已过期，请联系管理员！");
      } else if (exception instanceof DisabledException) {
         error.setMessage("账户被禁用，请联系管理员!");
      } else if (exception instanceof BadCredentialsException) {
         error.setMessage("用户名或密码错误，请重新输入！");
      }
      String s = new ObjectMapper().writeValueAsString(error);
      out.write(s);
      out.flush();
      out.close();
   }
}
