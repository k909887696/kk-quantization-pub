package com.kk.quantizationapi.service.threaddemo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: kk
 * @Date: 2021/11/25 17:34
 */
@Slf4j
public class Demo2Run implements  Runnable {
    @Override
    public void run() {

        log.info("implements Runnable is running");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("implements Runnable is finish");
    }
}
