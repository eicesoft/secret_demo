package org.secreetdemo.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();

        int userId = StpUtil.getLoginIdAsInt();

        if (userId == 10000) {
            list.add("user.add");
            list.add("user.update");
            list.add("user.get");
            list.add("art.*");
        } else {
            list.add("user.add");
            list.add("user.get");
        }

        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        int userId = (Integer) StpUtil.getLoginId();
        List<String> list = new ArrayList<String>();
        if (userId == 10000) {
            list.add("admin");
        } else {
            list.add("user");
        }

        return list;
    }
}

