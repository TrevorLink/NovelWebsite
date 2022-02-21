package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户实体类
 * @author HuangSir
 * @date 2022-02-21 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
   private Integer id;
   private String username;
   private  String password;
   private List<Novel> collections;
}
