package org.secretdemo.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import org.secretdemo.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 全局异常处理器
 *
 * @author kelezyb
 * @since 2025-03-31
 */
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

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R<Object> handleNotFoundException(Exception e) {
        return R.ok(null, "Not found：" + e.getMessage(), 502);
    }

    /**
     * 处理 Sa-Token 未登录异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<Object> handleException(Exception e) {
        return R.ok(null, "Error：" + e.getMessage(), 502);
    }
}