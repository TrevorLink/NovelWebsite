package com.yep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.UserMapper;
import com.yep.pojo.User;
import com.yep.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author HuangSir
 * @date 2022-02-21 14:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
