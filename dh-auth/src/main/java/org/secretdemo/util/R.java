package org.secretdemo.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Controller action 结果
 *
 * @param <T>
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS_CODE = 200;

    private int code;
    private String msg;
    private T data;

    public R(T data) {
        this(data, "", 200);
    }

    public R(T data, String msg, int code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static <T> R<T> ok() {
        return new R<>(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data, "", SUCCESS_CODE);
    }

    public static <T> R<T> ok(T data, String msg, int code) {
        return new R<>(data, msg, code);
    }
}
