package com.yep.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应信息
 * @author HuangSir
 * @date 2022-02-22 16:53
 */
@Data
@ApiModel("分页响应信息")
@AllArgsConstructor
@NoArgsConstructor
public class RespPage {
   @ApiModelProperty("总记录数")
   private  Long count;
   @ApiModelProperty("分页数据")
   private List<NovelPage> novelPageList;
}
