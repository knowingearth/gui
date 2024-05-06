package com.y.gui.service.impl;

import com.y.gui.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    @Async("dataPool")
    @Override
    public CompletableFuture<Long> taskOne() {
        log.info("AsyncServiceImpl.taskOne");
        return CompletableFuture.completedFuture(1L);
    }

    @Async
    @Override
    public CompletableFuture<String> taskTwo() {
        log.info("AsyncServiceImpl.taskTwo");
        return CompletableFuture.completedFuture("two");
    }

    @Async
    @Override
    public CompletableFuture<String> taskThree() {
        log.info("AsyncServiceImpl.taskThree");
        return CompletableFuture.completedFuture("three");
    }
}
