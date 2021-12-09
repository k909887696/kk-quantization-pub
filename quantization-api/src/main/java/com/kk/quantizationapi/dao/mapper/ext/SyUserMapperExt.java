package com.kk.quantizationapi.dao.mapper.ext;


import com.kk.quantizationapi.dao.entity.SyUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface SyUserMapperExt extends Mapper<SyUser> {


    List<SyUser> getAll();
    void addUser(SyUser u);
}
