package com.kk.quantizationapi.bean.res;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: kk
 * @Date: 2021/11/17 17:59
 */
public class ValuesRes implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="字典编号",example = "string")
    private String id;

    @ApiModelProperty("字典数据")
    private String value;

    @ApiModelProperty(value="优先级",example = "10")
    private int order;

    @ApiModelProperty("备注")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

