package com.y.gui.controller;

import com.y.gui.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/gui")
public class TestController {
    @Autowired
    public RedisUtil cache;

    @RequestMapping("/hello")
    public String test() {
        return "htllo world";
    }
}
