package com.example.controller;

//import com.example.mapper.UserMapper;
import com.example.entity.Account;
import com.example.entity.resp.RestBean;
import com.example.repo.AccountRepository;
import com.example.service.AccountService;
import com.example.service.VerifyService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author loop
 */
@RestController
@RequestMapping("/api/auth")
public class ApiController {

    @Resource
    VerifyService verifyService;

    @Resource
    AccountService accountService;



    @RequestMapping("/verify-code")
    public RestBean<Void> cerifyCode(@RequestParam("email") String email) {
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200, "发送成功");
        } catch (Exception e) {
            return new RestBean<>(500, "发送失败");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestBean<Void> register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("verify") String verify) {
        if (verifyService.checkCode(email, verify)) {
            accountService.createAccount(username, password);
            return new RestBean<>(200, "注册成功");
        } else {
            return new RestBean<>(403, "注册失败，验证码填写错误");
        }
    }

}
