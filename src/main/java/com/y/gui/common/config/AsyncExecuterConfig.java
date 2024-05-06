package com.y.gui.common.config;

import lombok.Data;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.task.async-execution.pool")
public class AsyncExecuterConfig implements AsyncConfigurer {
    private Integer coreSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private Integer keepAliveSeconds;

    private Boolean allowCoreThreadTimeOut;

    private String threadNamePrefix;

    /**
     * 未指定线程名，线程池默认名称为方法名，即【asyncExecutor】
     * @return
     */
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        build(executor);
        return executor;
    }

    /**
     * 初始化线程池配置参数
     * @param executor
     */
    private void build(ThreadPoolTaskExecutor executor) {
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
