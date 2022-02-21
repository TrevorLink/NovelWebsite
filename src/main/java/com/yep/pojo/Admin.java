package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author HuangSir
 * @date 2022-02-21 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
   private Integer id;
   private String username;
   private  String password;
}
