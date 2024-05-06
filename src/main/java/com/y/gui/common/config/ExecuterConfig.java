package com.y.gui.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.task.data-execution.pool")
public class ExecuterConfig {
    private Integer coreSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private Integer keepAliveSeconds;

    private Boolean allowCoreThreadTimeOut;

    private String threadNamePrefix;

    /**
     * 指定了线程池名称【dataPool】；如果未指定，线程池默认名称为方法名，即【dataExecutor】
     * @return
     */
    @Bean("dataPool")
    public Executor dataExecutor() {
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
}
