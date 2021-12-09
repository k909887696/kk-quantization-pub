package com.kk.quantizationapi.bean.vo;

import com.kk.quantizationapi.bean.common.PageVo;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:27
 */

public class ValuesVo extends PageVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="字典编号",example = "string")
    private String id;
    @ApiModelProperty("字典数据")
    private String value;

    @ApiModelProperty(value="优先级",example = "10")
    @Max(value=100,message = "优先级最大为100")
    private int order;

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
