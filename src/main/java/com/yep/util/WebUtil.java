package com.yep.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author HuangSir
 * @date 2022-02-22 16:23
 */
public class WebUtil {
   /**
    * 向响应头写数据
    * @param response
    * @param obj
    * @throws IOException
    */
   public static void writeObjectAsResponse(HttpServletResponse response, Object obj) throws IOException {
      response.setContentType("application/json;charset=utf-8");
      PrintWriter responseWriter = response.getWriter();
      responseWriter.write(new ObjectMapper().writeValueAsString(obj));
   }
}
