package com.kk.quantizationapi.service.threaddemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Author: kk
 * @Date: 2021/11/25 17:45
 */
@Slf4j
public class DemoCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("Callable<> running...");
        Thread.sleep(5000);
        log.info("Callable<> finish...");
        return "Callable<String>";
    }
}
