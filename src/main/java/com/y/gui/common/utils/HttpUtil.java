package com.y.gui.common.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * HTTP请求
 */
public class HttpUtil {

    /**
     * HTTP请求
     */
    public static <R, P> R exchange(String url, HttpMethod method, Class<R> responseBodyType, P requestBody) {
        return new RestTemplate().exchange(url, method, new HttpEntity<>(requestBody), responseBodyType).getBody();
    }
}
