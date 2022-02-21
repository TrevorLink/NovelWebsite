package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小说实体类
 * @author HuangSir
 * @date 2022-02-21 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Novel {
   private  Integer id;
   private String name;
   private  String type;
   private  String author;
   private  String description;
   private  Integer status;//小说当前状态
   private  Integer hot;//小说热度
}
