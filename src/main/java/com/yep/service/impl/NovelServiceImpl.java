package com.yep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.NovelMapper;
import com.yep.pojo.Novel;
import com.yep.service.NovelService;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-21 14:44
 */
@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
}
