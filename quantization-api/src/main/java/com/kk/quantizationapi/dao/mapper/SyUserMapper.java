package com.kk.quantizationapi.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kk.quantizationapi.dao.entity.SyUser;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;


public interface SyUserMapper extends Mapper<SyUser> {


    List<SyUser> getAll();
    void addUser(SyUser u);
}
