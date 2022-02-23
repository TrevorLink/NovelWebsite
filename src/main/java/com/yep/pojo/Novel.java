package com.yep.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("小说实体类")
@NoArgsConstructor
public class Novel {
   @ApiModelProperty("小说ID")
   private  Integer id;
   @ApiModelProperty("小说标题")
   private String name;
   @ApiModelProperty("小说类型")
   private  String type;
   @ApiModelProperty("小说作者")
   private  String author;
   @ApiModelProperty("小说描述简介")
   private  String description;
   @ApiModelProperty("小说当前状态")
   private  Integer status;
   @ApiModelProperty("小说封面URL")
   private String imageUrl;
   @ApiModelProperty("小说下载URL")
   private String downloadUrl;
   @ApiModelProperty("小说热度")
   private  Integer hot;
}
