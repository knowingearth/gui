package com.y.gui.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<Long> taskOne();

    CompletableFuture<String> taskTwo();

    CompletableFuture<String> taskThree();
}
