package com.yep.pojo;

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
@AllArgsConstructor
@NoArgsConstructor
public class RespPage {
   private  Long count;
   private List<NovelPage> novelPageList;
}
