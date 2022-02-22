package com.yep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yep.pojo.Novel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:43
 */
@Repository
public interface NovelMapper extends BaseMapper<Novel> {
   Integer addCollection(@Param("userId") Integer userId, @Param("id") Integer id);

   List<Novel> getCurrentUserNovelCollections(@Param("userId") Integer userId);
}
