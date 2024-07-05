package com.y.gui.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class XMLUtil {

    /**
     * xml字符串转bean
     * @param xml
     * @param c
     * @return
     * @param <T>
     */
    public static <T> T xmlToBean(String xml, Class<T> c) {
        if (!StringUtils.hasText(xml)) {
            return null;
        }
        try {
            return new XmlMapper().readValue(xml, c);
        } catch (JsonProcessingException e) {
            log.info("XMLUtil.xmlToBean, JsonProcessingException, xml:{}, class:{}", xml, c);
        }
        return null;
    }

    /**
     * bean转xml字符串
     * @param obj
     * @return
     */
    public static String beanToXml(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            ObjectMapper xmlMapper = new XmlMapper();
            // 美化输出
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return xmlMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.info("XMLUtil.beanToXml, JsonProcessingException, obj:{}", JSON.toJSONString(obj));
        }
        return null;
    }
}
