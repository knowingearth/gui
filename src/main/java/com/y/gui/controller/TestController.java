package com.y.gui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gui")
public class TestController {

    @RequestMapping("/hello")
    public String test() {
        return "htllo world";
    }
}
