package com.kk.quantizationapi.dao.mapper.ext;

import com.kk.quantizationapi.dao.entity.CnM;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface CnMMapperExt extends Mapper<CnM> {


    List<CnM> getAll();
}
