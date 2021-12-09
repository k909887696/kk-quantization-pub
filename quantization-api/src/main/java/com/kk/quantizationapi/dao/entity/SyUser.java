package com.kk.quantizationapi.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sy_user") //指定数据库的表明
public class SyUser extends Model<SyUser>{
    @Id
    private  String userId;
    @TableField("user_name")
    private  String userName;
    @TableField(value = "b_day",jdbcType = JdbcType.DATE)
    private Date BDay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBDay() {
        return BDay;
    }

    public void setBDay(Date BDay) {
        this.BDay = BDay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
