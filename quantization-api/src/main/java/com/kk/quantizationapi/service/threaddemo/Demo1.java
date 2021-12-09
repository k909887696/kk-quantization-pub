package com.kk.quantizationapi.service.threaddemo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: kk
 * @Date: 2021/11/25 10:04
 */
@Slf4j
public class Demo1 extends Thread{

    public void run()
    {


        log.info(getName()+"is running...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(getName()+"is finish...");
    }


}
