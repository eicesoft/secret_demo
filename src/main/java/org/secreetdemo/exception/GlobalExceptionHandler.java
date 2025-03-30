package org.secreetdemo.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import org.secreetdemo.http.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 Sa-Token 权限不足异常
     */
    @ExceptionHandler(NotPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public SaResult handleSaAuthorizeException(NotPermissionException e) {
        System.out.println("---------- 进入 Sa-Token 权限不足异常处理 -----------");
        return SaResult.error("权限不足：" + e.getMessage());
    }

    /**
     * 处理 Sa-Token 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SaResult handleNotLoginException(NotLoginException e) {
        System.out.println("---------- 进入 Sa-Token 未登录异常处理 -----------");
        return SaResult.error("未登录或登录已过期：" + e.getMessage());
    }

    // 可以添加其他异常的处理方法

    /**
     * 处理 Sa-Token 未登录异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<Object> handleExcception(Exception e) {
        return R.ok(null, "未登录或登录已过期：" + e.getMessage(), 502);
    }
}