package com.y.gui.service.impl;

import com.y.gui.annotation.CLog;
import com.y.gui.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Override
    @CLog
    public String getName() {
        return "王五";
    }

    @Override
    @CLog
    public void updateName(String name) {
        log.info("TestServiceImpl.updateName, name:{}", name);
    }
}
