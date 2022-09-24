package com.LuckyBai.Bicycle.Controller;

import com.LuckyBai.Bicycle.Common.Result;
import com.LuckyBai.Bicycle.Entity.User;
import com.LuckyBai.Bicycle.Service.UserService;
import com.LuckyBai.Bicycle.utils.PhoneUtils;
import com.LuckyBai.Bicycle.utils.SendSms;
import com.LuckyBai.Bicycle.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, @RequestBody User user) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, user.getPhone());
        User em = userService.getOne(queryWrapper);

        if (em == null) {
            return Result.error("登录失败");
        }
        String md5Password = getMD5Password(user.getPassword(), user.getSalt());
        if (!md5Password.equals(em.getPassword())) {
            return Result.error("登录失败mima");
        }

        request.getSession().setAttribute("user", em.getId());
        return Result.success(em);
    }

    private String getMD5Password(String password, String salt){
        for (int i = 1;i < 3; i++){
            password = DigestUtils.md5DigestAsHex((salt+password).getBytes()).toUpperCase();
        }
        return password;
    }

    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user,HttpSession session){
        //获取手机号
        String phone = user.getPhone();
        //验证手机格式
        if (!PhoneUtils.isMobile(phone)){
             return Result.error("手机格式错误");
        }
        if(StringUtils.isNotEmpty(phone)){
            //判断redis中是否已经发送过验证码
            String key = phone+"::code";
            Object o = new Object();
            o = redisTemplate.opsForValue().get(key);
            if (o != null){
                Long timeto = redisTemplate.opsForValue().getOperations().getExpire(key);
                return Result.error("请"+timeto+"秒后重试");
            }
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //阿里云短信服务发送短信
//            SendSms.sendMsg(phone,code);
//            将手机号存在session中
            session.setAttribute("phone",phone);
            System.err.println("短信验证码"+code);
            redisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
            return Result.success("手机验证码发送成功！");
        }
        return Result.error("短信发送失败！");
    }

    @PostMapping("/msglogin")
    public Result<String> Msglogin(@RequestBody User user){
        return new Result<>();
    }

    public Result<User> register(@RequestBody User user,HttpSession session) {
        log.info("phone:{}", user.getPhone());
        log.info("password:{}", user.getPassword());
        String phone = (String) session.getAttribute("phone");
        if (StringUtils.isEmpty(phone)) {
//            return Result.error("手机号不允许为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return Result.error("密码不允许为空");
        }
        user.setPhone(phone);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, user.getPhone());
        User em = userService.getOne(queryWrapper);
        if (em == null) {
            userService.save(user);
            log.info("user:{}", user);
            return Result.success(user);
        } else {
            return Result.error("注册失败，账户已经存在");
        }
    }

//    @PostMapping("/logout")
//    public Result<String> logout(HttpServletRequest request) {
//        request.getSession().removeAttribute("user");
//        return Result.success("退出成功");
//    }
}