package com.y.gui.controller;

import com.alibaba.fastjson.JSON;
import com.y.gui.common.bases.ResultEntity;
import com.y.gui.common.utils.XMLUtil;
import com.y.gui.param.XmlParam;
import com.y.gui.vo.XmlVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * XML相关操作Controller
 */
@Slf4j
@RestController
@RequestMapping("xml")
public class XmlController {

    /**
     * 请求参数xml无法直接转换为obj时，参数可以用字符串接收，然后手动转换
     * Content-Type = application/xml
     * @param body example <xmlVO><userName>张三</userName></xmlVO>
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "strParam", produces = MediaType.APPLICATION_XML_VALUE)
    public XmlVO strParam(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
        // 有时候和外部对接接口，要求返回的XML指定编码格式：<?xml version="1.0" encoding="utf-8"?>
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // 请求路径后拼接的参数：http://localhost:8080/xml/strParam?userName=abc&age=18
        Map<String, String[]> parameterMap = request.getParameterMap();

        // 支持转换为第三方依赖的对象:示例
        // WinOperaSupport layout = XMLUtil.xmlToBean(body, WinOperaSupport.class);

        // 转换为目标对象
        XmlVO vo = XMLUtil.xmlToBean(body, XmlVO.class);
        vo = new XmlVO();
        vo.setUserName("张三");
        vo.setAddress("北京");
        vo.setAge(20);
        log.info("XmlController.strParam, vo:{}", JSON.toJSONString(vo));

        return vo;
    }

    /**
     * 请求参数xml可以映射到自己定义的obj时
     * Content-Type = application/xml
     * @param xmlParam example <xmlVO><userName>张三</userName></xmlVO>
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "objParam", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResultEntity<XmlVO> strParam(@RequestBody XmlParam xmlParam, HttpServletRequest request, HttpServletResponse response) {
        // 有时候和外部对接接口，要求返回的XML指定编码格式：<?xml version="1.0" encoding="utf-8"?>
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        log.info("XmlController.strParam, xmlParam:{}", JSON.toJSONString(xmlParam));
        XmlVO xmlVO = new XmlVO();
        BeanUtils.copyProperties(xmlParam, xmlVO);
        xmlVO.setAddress("北京市东城区");
        log.info("XmlController.strParam, xmlVO:{}", JSON.toJSONString(xmlVO));
        log.info("XmlController.strParam, xml:{}", XMLUtil.beanToXml(xmlVO));
        return ResultEntity.getSuccessInstance(xmlVO);
    }
}
