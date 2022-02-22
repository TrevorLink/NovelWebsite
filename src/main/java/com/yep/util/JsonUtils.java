package com.yep.util;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类
 * @author HuangSir
 * @date 2022-02-22 16:04
 */
public class JsonUtils extends JSON {


   public static String parseToString(Object object) {

      return toJSONString(object);

   }

   public static <T> T parseToObject(String text, Class<T> clazz) {
      return parseObject(text, clazz);
   }

}
