package com.kk.quantizationapi.dao.mapper;

import com.kk.quantizationapi.dao.entity.CnM;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CnMMapper extends Mapper<CnM> {


    List<CnM> getAll();
}
