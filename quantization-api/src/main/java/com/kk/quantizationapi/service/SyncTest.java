package com.kk.quantizationapi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kk.quantizationapi.common.exception.BusinessException;
import com.kk.quantizationapi.constant.ResponseCode;
import com.kk.quantizationapi.dao.entity.CnM;
import com.kk.quantizationapi.dao.entity.Daily;
import com.kk.quantizationapi.dao.entity.SyUser;
import com.kk.quantizationapi.dao.mapper.CnMMapper;
import com.kk.quantizationapi.dao.mapper.DailyMapper;
import com.kk.quantizationapi.dao.mapper.SyUserMapper;
import com.kk.quantizationapi.service.threaddemo.Demo1;
import com.kk.quantizationapi.service.threaddemo.Demo2Run;
import com.kk.quantizationapi.service.threaddemo.DemoCallable;
import com.kk.quantizationapi.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: kk
 * @Date: 2021/11/25 9:52
 */
@Slf4j
@Component
public class SyncTest {

    private int tc=5;
    @Resource
    public SyUserMapper syUserMapper;
    @Resource
    public CnMMapper cnMMapper;
    @Resource
    public DailyMapper dailyMapper;

    public List<Daily> getDailyList()
    {

        IPage<Daily> p = new Page<Daily>(1,2);
        QueryWrapper<Daily> query = new QueryWrapper<>();
        query.eq("trade_date","20210701");
        p  = dailyMapper.selectPage(p,query);
        //开始分页后直接new PageInfo对象,将list放进去

        return p.getRecords();
    }
    public  List<SyUser> getuser()
    {

        /*List<SyUser> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            SyUser user = new SyUser();
            user.setUserId("kk"+ UUID.randomUUID());
            user.setUserName("lzk"+i);
            list.add(user);
            syUserMapper.insert(user);
        }*/

        PageHelper.startPage(1,1);
        List<SyUser> userList = syUserMapper.selectAll();
        //开始分页后直接new PageInfo对象,将list放进去
        PageInfo<SyUser> pageInfo = new PageInfo<>(userList);

        log.info(JsonUtil.getJSONString(userList));
        log.info(JsonUtil.getJSONString(pageInfo));
        return userList;
        //throw new BusinessException(ResponseCode.BUSINESS_EXCEPTION.getCode(),"test异常处理");
    }

    public List<CnM> getCnMListPage()
    {
        PageHelper.startPage(1,10);
        List<CnM> userList = cnMMapper.getAll();
        //开始分页后直接new PageInfo对象,将list放进去
        PageInfo<CnM> pageInfo = new PageInfo<>(userList);

        log.info(JsonUtil.getJSONString(userList));
        log.info(JsonUtil.getJSONString(pageInfo));
        return userList;
    }

    public void extendThread() throws ExecutionException, InterruptedException {

        Demo1 demo1 = new Demo1();
        Demo1 demo2 = new Demo1();
        demo1.start();
        demo2.start();



        Thread r1 = new Thread(new Demo2Run());
        Thread r2 = new Thread(new Demo2Run());

        r1.start();
        r2.start();

        new Thread() {
            public void run() {
                log.info("匿名内部类创建线程方式1...");
            };
        }.start();


        DemoCallable d = new DemoCallable();
		/*	call()只是线程任务,对线程任务进行封装
			class FutureTask<V> implements RunnableFuture<V>
			interface RunnableFuture<V> extends Runnable, Future<V>
		*/
        FutureTask<String> task = new FutureTask<>(d);
        Thread t = new Thread(task);
        t.start();
        log.info("提前完成任务...");
        //获取任务执行后返回的结果
        String result = task.get();
        log.info("线程执行结果为"+result);

       /* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("定时任务延迟0(即立刻执行),每隔1000ms执行一次");
                tc--;
                if(tc<=0) {
                    log.info("定时任务延迟,finish");
                    cancel();
                }

            }
        }, 0, 1000);*/
//创建带有5个线程的线程池
        //返回的实际上是ExecutorService,而ExecutorService是Executor的子接口
        Executor threadPool = Executors.newFixedThreadPool(10);
        for(int i = 0 ;i < 10 ; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    log.info(Thread.currentThread().getName()+" is running");
                }
            });
        }


    }
}
