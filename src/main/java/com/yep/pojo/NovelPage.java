package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小说分页数据
 * @author HuangSir
 * @date 2022-02-22 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovelPage {
   private Integer id;
   private String imageUrl;
   private String name;
}
