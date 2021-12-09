package com.kk.quantizationapi.constant;

/**
 * @Author: kk
 * @Date: 2021/11/17 13:52
 * 统一响应码
 */
public enum ResponseCode {
    OK("200", "处理成功"),
    NOT_FOUND("902", "暂无符合条件的相关数据"),
    INVALID_PARAMETER("903", "无效的请求参数"),
    NO_PERMISSION_SYSTEM("403", "没有系统操作权限"),
    SYSTEM_EXCEPTION("500", "系统异常"),
    ILLEGAL_OPERATION("899", "非法操作，请通过正常途径操作！"),
    BUSINESS_EXCEPTION("900", "业务异常"),
    BUSINESS_PARAMETER_EXCEPTION("905","参数异常"),
    LOGIN_OUT("999","用户未登录，请登录！"),
    NO_PERMISSION_DATA("997", "对不起，您没有该权限，请联系管理员！"),
    NO_PERMISSION_OPERATION("998", "对不起，您没有该权限，请联系管理员！");


    private String code;

    private String desc;

    private ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
