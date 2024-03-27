package com.y.gui.controller;

import com.y.gui.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gui")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public String test() {
        String name = testService.getName();
        testService.updateName(name);
        return name;
    }
}
