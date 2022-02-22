package com.yep.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yep.pojo.RespBean;
import com.yep.util.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登出成功处理器
 * @author HuangSir
 * @date 2022-02-22 16:20
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
   @Override
   public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
      WebUtil.writeObjectAsResponse(httpServletResponse,RespBean.ok("登出成功！"));
   }
}
