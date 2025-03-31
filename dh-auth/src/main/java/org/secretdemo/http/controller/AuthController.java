package org.secretdemo.http.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.secretdemo.domain.dto.UserDto;
import org.secretdemo.http.response.TestResponse;
import org.secretdemo.util.R;
import org.secretdemo.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserMapper userMapper;

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
        String id = (String) StpUtil.getLoginId();
        List<String> permissions = StpUtil.getPermissionList();
        return R.ok(permissions);
    }

    @SaCheckPermission("user.update")
    @GetMapping("test2")
    public R<TestResponse> test2() {
        String id = (String) StpUtil.getLoginId();
        List<String> permissions = StpUtil.getPermissionList();
        TestResponse resp = new TestResponse();
        resp.setUserId(id);
        resp.setPermissions(permissions);
        return R.ok(resp);
    }

    @GetMapping("test3")
    public R<UserDto> test3() {
        UserDto user = userMapper.selectById2(111L);

        return R.ok(user);
    }

    @GetMapping("kick")
    public R<Void> kick() {
        StpUtil.kickout(StpUtil.getLoginId());
        return R.ok();
    }


    @GetMapping("logout")
    public R<String> logout() {
        StpUtil.logout();

        return R.ok();
    }
}
