package org.secretdemo.service;

import org.secretdemo.config.TokenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dh-auth", configuration = TokenFeignConfig.class)
public interface AuthService {
    @GetMapping(value = "/auth/login")
    String login(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping(value = "/auth/test")
    String test();
}
