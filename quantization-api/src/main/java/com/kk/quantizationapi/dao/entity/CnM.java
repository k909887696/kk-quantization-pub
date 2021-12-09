package com.kk.quantizationapi.dao.entity;



import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cn_m")
public class CnM  {

    @Id
    private String month;

    private Double m0;

    private Double m1;

    private Double m2;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getM0() {
        return m0;
    }

    public void setM0(Double m0) {
        this.m0 = m0;
    }

    public Double getM1() {
        return m1;
    }

    public void setM1(Double m1) {
        this.m1 = m1;
    }

    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }
}
