package com.kk.quantizationapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kk
 * @since 2021-12-07
 */
@TableName("daily")
@ApiModel(value = "Daily对象", description = "")
public class Daily implements Serializable {

    private static final long serialVersionUID = 1L;
    @MppMultiId
    @ApiModelProperty("股票代码")
    @TableId("ts_code")
    private String tsCode;
    @MppMultiId
    @ApiModelProperty("交易日期")
    @TableId("trade_date")
    private String tradeDate;

    @ApiModelProperty("开盘价")
    @TableField("open")
    private Double open;

    @ApiModelProperty("最高价")
    @TableField("high")
    private Double high;

    @ApiModelProperty("最低价")
    @TableField("low")
    private Double low;

    @ApiModelProperty("收盘价")
    @TableField("close")
    private Double close;

    @ApiModelProperty("上一日收盘价")
    @TableField("pre_close")
    private Double preClose;

    @ApiModelProperty("涨跌额")
    @TableField("change")
    private Double change;

    @ApiModelProperty("涨跌幅%")
    @TableField("pct_chg")
    private Double pctChg;

    @ApiModelProperty("成交额")
    @TableField("vol")
    private Double vol;

    @ApiModelProperty("成交量")
    @TableField("amount")
    private Double amount;

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }
    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }
    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }
    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
    public Double getPreClose() {
        return preClose;
    }

    public void setPreClose(Double preClose) {
        this.preClose = preClose;
    }
    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
    public Double getPctChg() {
        return pctChg;
    }

    public void setPctChg(Double pctChg) {
        this.pctChg = pctChg;
    }
    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Daily{" +
            "tsCode=" + tsCode +
            ", tradeDate=" + tradeDate +
            ", open=" + open +
            ", high=" + high +
            ", low=" + low +
            ", close=" + close +
            ", preClose=" + preClose +
            ", change=" + change +
            ", pctChg=" + pctChg +
            ", vol=" + vol +
            ", amount=" + amount +
        "}";
    }
}
