package com.kk.quantizationapi.service.threaddemo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: kk
 * @Date: 2021/11/25 18:32
 */
@Service
public class AsyncService {

    @Async
    public void Async_A() {
        System.out.println("Async_A is running");
    }

    @Async
    public void Async_B() {
        System.out.println("Async_B is running");
    }

}
