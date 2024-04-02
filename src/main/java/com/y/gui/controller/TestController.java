package com.y.gui.controller;

import com.alibaba.fastjson.JSON;
import com.y.gui.common.bases.Page;
import com.y.gui.common.bases.ResultEntity;
import com.y.gui.dto.UserDTO;
import com.y.gui.service.UserQueryService;
import com.y.gui.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gui")
public class TestController {
    @Autowired
    private UserQueryService service;

    @RequestMapping("/hello")
    public ResultEntity<Page<List<UserDTO>>> test(@RequestBody UserVO user) {
        Page<List<UserDTO>> page = service.queryUser(user);
        log.info("TestController.test, page:{}", JSON.toJSONString(page));
        return ResultEntity.getSuccessInstance(page);
    }
}
