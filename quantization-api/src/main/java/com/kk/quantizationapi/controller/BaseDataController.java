package com.kk.quantizationapi.controller;

import com.kk.quantizationapi.bean.common.PageVo;
import com.kk.quantizationapi.bean.res.ValuesRes;
import com.kk.quantizationapi.bean.vo.ValuesVo;
import com.kk.quantizationapi.common.ApiResult;
import com.kk.quantizationapi.common.exception.BusinessException;
import com.kk.quantizationapi.constant.ResponseCode;
import com.kk.quantizationapi.dao.entity.CnM;
import com.kk.quantizationapi.dao.entity.Daily;
import com.kk.quantizationapi.dao.entity.SyUser;
import com.kk.quantizationapi.dao.mapper.CnMMapper;
import com.kk.quantizationapi.dao.mapper.SyUserMapper;
import com.kk.quantizationapi.service.SyncTest;
import com.kk.quantizationapi.service.threaddemo.Demo2Run;
import com.kk.quantizationapi.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: kk
 * @Date: 2021/11/15 16:23
 */
@Api(value = "/api/v1/basedata",tags = "基础数据")
@ResponseBody
@RestController
@RequestMapping(("/api/v1/basedata"))
@Slf4j
public class BaseDataController {

    @Resource
    public CnMMapper cnMMapper;
    @Resource
   public  SyncTest syncTest;
    @Resource
    public SyUserMapper syUserMapper;
    @ApiOperation("获取字典列表")
    @ApiImplicitParams(  {
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String"),
    })
    @PostMapping("/valueslist")
    public ApiResult<ValuesRes> QueryValuesList(@Valid @RequestBody ValuesVo vo) throws Exception {
        log.info("test-slf4j");
        ValuesRes res = new ValuesRes();
        ApiResult<ValuesRes> response = new ApiResult(res);
        log.info(JsonUtil.getJSONString(cnMMapper.getAll()));

        return response;

    }

    @ApiOperation("testThread")
    @ApiImplicitParams(  {
           // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
           // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
           // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
           // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/testThread")
    public ApiResult<List<CnM>> TestThread(@Valid @RequestBody ValuesVo vo) throws Exception {
        log.info("testThread===================");
       // SyncTest st = new  SyncTest();
        syncTest.getuser();

        return new  ApiResult(syncTest.getCnMListPage());

    }

    @ApiOperation("query_user_list")
    @ApiImplicitParams(  {
            // @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = true, dataType = "String"),
            // @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = true, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header",  dataType = "String")
    })
    @PostMapping("/query_user_list")
    public ApiResult<List<Daily>> query_user_list(@Valid @RequestBody ValuesVo vo) throws Exception {
        log.info("query_user_list===================");
        // SyncTest st = new  SyncTest();
        //syncTest.getuser();

        return new  ApiResult(syncTest.getDailyList());

    }
}
