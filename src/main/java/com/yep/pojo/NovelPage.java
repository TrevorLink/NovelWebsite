package com.yep.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小说分页数据
 * @author HuangSir
 * @date 2022-02-22 16:53
 */
@Data
@ApiModel("小说分页数据")
@AllArgsConstructor
@NoArgsConstructor
public class NovelPage {
   @ApiModelProperty("小说ID")
   private Integer id;
   @ApiModelProperty("小说封面URL")
   private String imageUrl;
   @ApiModelProperty("小说标题")
   private String name;
}
