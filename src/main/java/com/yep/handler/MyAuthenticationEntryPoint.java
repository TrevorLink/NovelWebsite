package com.yep.handler;

import com.yep.pojo.RespBean;
import com.yep.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录处理器
 * @author HuangSir
 * @date 2022-02-22 16:23
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
   @Override
   public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      WebUtil.writeObjectAsResponse(httpServletResponse, RespBean.error("未登录，请先登录"));
   }
}
