package org.secretdemo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.secretdemo.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private AuthService authService;

    @RequestMapping("/test")
    public String test() {
        return this.authService.login("admin", "123456");
    }

    @RequestMapping("/test2")
    public String test2() {
        return this.authService.test();
    }
}
