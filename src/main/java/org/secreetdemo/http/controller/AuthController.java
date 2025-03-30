package org.secreetdemo.http.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.secreetdemo.http.response.TestResponse;
import org.secreetdemo.http.result.R;
import cn.dev33.satoken.annotation.SaCheckLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public R<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return R.ok(StpUtil.getTokenValue());
        }

        if ("admin".equals(username) && "123456".equals(password)) {
            StpUtil.login(10000);
            return R.ok(StpUtil.getTokenValue());
        }

        return R.ok("登录失败");
    }

    @SaCheckPermission("user.add")
    @GetMapping("test")
    public R<List<String>> test() {
        String id = (String)StpUtil.getLoginId();
        List<String> permissions = StpUtil.getPermissionList();
        return R.ok(permissions);
    }

    @SaCheckPermission("user.update")
    @GetMapping("test2")
    public R<TestResponse> test2() {
        String id = (String)StpUtil.getLoginId();
        List<String> permissions = StpUtil.getPermissionList();
        TestResponse resp = new TestResponse();
        resp.setUserId(id);
        resp.setPermissions(permissions);
        return R.ok(resp);
    }

    @GetMapping("kick")
    public R<Void> kick() {
        StpUtil.kickout(StpUtil.getLoginId());
        return R.ok();
    }


    @SaCheckLogin
    @GetMapping("logout")
    public R<String> logout() {
        StpUtil.logout();

        return R.ok();
    }
}
