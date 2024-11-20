package com.y.gui.common.event.subscribe;

import com.y.gui.common.event.publish.PushTestEvent;
import com.y.gui.service.PublishContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class TestEventListener implements ApplicationListener<PushTestEvent> {
    @Resource
    private PublishContentService publishContentService;

    @Override
    public void onApplicationEvent(PushTestEvent event) {
        publishContentService.test(event);
    }
}
