package com.LuckyBai.Bicycle.Service.ServiceImpl;

import com.LuckyBai.Bicycle.Entity.User;
import com.LuckyBai.Bicycle.Mapper.UserMapper;
import com.LuckyBai.Bicycle.Service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
}

