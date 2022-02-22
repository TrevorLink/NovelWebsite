package com.yep.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yep.pojo.RespBean;
import com.yep.util.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户登录成功的处理器
 * @author HuangSir
 * @date 2022-02-22 15:58
 */
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
   @Override
   public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
      WebUtil.writeObjectAsResponse(httpServletResponse,RespBean.ok("登陆成功！"));
   }
}
