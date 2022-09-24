package com.LuckyBai.Bicycle.Mapper;

import com.LuckyBai.Bicycle.Entity.User;
import com.LuckyBai.Bicycle.Service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//public class UseeTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void login(){
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername,"123");
//        User one = userService.getOne(queryWrapper);
//        System.err.println(one);
//    }
//}
