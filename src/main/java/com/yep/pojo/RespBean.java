package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口的公共响应类型
 * @author HuangSir
 * @date 2022-02-22 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
   private  int code;
   private  String message;
   private Object data;
   public static RespBean ok(String message,Object data){
      return new RespBean(200,message,data);
   }
   public static RespBean ok(String message){
      return new RespBean(200,message,null);
   }
   public static RespBean error(String message,Object data){
      return new RespBean(500,message,data);
   }
   public static RespBean error(String message){
      return new RespBean(500,message,null);
   }
}
