package com.example.controller;

//import com.example.mapper.UserMapper;
import com.example.entity.resp.RestBean;
import com.example.service.AccountService;
import com.example.service.VerifyService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author loop
 */
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Resource
    VerifyService verifyService;

    @Resource
    AccountService accountService;

    @Resource
    StringRedisTemplate template;


    @GetMapping("/verify-code")
    public RestBean<Void> cerifyCode(@RequestParam("email") String email) {
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200, "发送成功");
        } catch (Exception e) {
            return new RestBean<>(500, "发送失败");
        }
    }

    @PostMapping("/register")
    public RestBean<Void> register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("verify") String verify) {
        if (verifyService.checkCode(email, verify)) {
            accountService.createAccount(username, password);
            template.delete("verifycode:" + email);
            return new RestBean<>(200, "注册成功");
        } else {
            return new RestBean<>(403, "注册失败，验证码填写错误");
        }
    }

    @PostMapping("/login-success")
    public RestBean<Void> loginSuccess() {
        return new RestBean<>(200, "登录成功");
    }

    @PostMapping("/login-failure")
    public RestBean<Void> loginFailure() {
        return new RestBean<>(304, "登录失败，用户名或密码错误");
    }


}
