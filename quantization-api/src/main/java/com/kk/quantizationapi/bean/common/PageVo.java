package com.kk.quantizationapi.bean.common;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

/**
 * @Author: kk
 * @Date: 2021/11/18 13:49
 */
public class PageVo {
    @ApiModelProperty(value="页索引",example = "1")
    @Min(value = 1,message="当前页索引不能为空!")
    public int pageIndex;
    @ApiModelProperty(value="页大小",example = "10")
    @Min(value = 1,message="每页大小不能为空!")
    public int pageSize;


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
