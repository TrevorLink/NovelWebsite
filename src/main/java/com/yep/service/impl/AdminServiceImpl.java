package com.yep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.AdminMapper;
import com.yep.pojo.Admin;
import com.yep.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-21 14:42
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
