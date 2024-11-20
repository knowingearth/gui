package com.y.gui.common.event.util;

import com.y.gui.common.event.publish.PushTestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class PushUtil {
    private static ApplicationEventPublisher publisher;
    @Resource
    private ApplicationEventPublisher publish;
    @PostConstruct
    private void initPublisher() {
        publisher = publish;
    }

    public static void testPushEvent() {
        PushTestEvent pushTestEvent = new PushTestEvent("");
        pushTestEvent.setId(1);
        pushTestEvent.setName("张三");
        publisher.publishEvent(pushTestEvent);
    }
}
