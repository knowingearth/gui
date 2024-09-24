package com.y.gui.common.utils;

import org.springframework.http.HttpMethod;

public class AreaData {
    public static void main(String[] args) {
        String url = "https://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/64.html";
        System.out.println(url);
        Object exchange = HttpUtil.exchange(url, HttpMethod.GET, Object.class, null);
        System.out.println(exchange);
    }

}
