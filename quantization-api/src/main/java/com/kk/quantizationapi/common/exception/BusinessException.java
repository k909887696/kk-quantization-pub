package com.kk.quantizationapi.common.exception;

/**
 * @Author: kk
 * @Date: 2021/11/18 15:30
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    String code = "-1";

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}