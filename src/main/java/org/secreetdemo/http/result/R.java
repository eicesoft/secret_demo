package org.secreetdemo.http.result;

import java.io.Serializable;

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
        return new R<T>(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(data, "", SUCCESS_CODE);
    }

    public static <T> R<T> ok(T data, String msg, int code) {
        return new R<T>(data, msg, code);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
