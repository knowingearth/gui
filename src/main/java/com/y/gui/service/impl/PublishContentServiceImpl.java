package com.y.gui.service.impl;

import com.alibaba.fastjson.JSON;
import com.y.gui.service.PublishContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublishContentServiceImpl implements PublishContentService {

    @Override
    public Boolean test(Object object) {
        log.info("PublishContentServiceImpl.test, object:{}", JSON.toJSONString(object));
        return Boolean.TRUE;
    }
}
