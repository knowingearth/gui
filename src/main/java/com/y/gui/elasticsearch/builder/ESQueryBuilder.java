package com.y.gui.elasticsearch.builder;

public class ESQueryBuilder {
    /*public static BoolQueryBuilder initPageQueryBuilder(EsQueryParam param) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.hasText(param.getSaleOrderId())) {
            boolQuery.must(QueryBuilders.matchQuery("saleOrderId", param.getSaleOrderId()));
        }
        if (StringUtils.hasText(param.getScmOrderId())) {
            boolQuery.must(QueryBuilders.matchQuery("id", param.getScmOrderId()));
        }
        if (StringUtils.hasText(param.getSpuCode())) {
            boolQuery.must(QueryBuilders.nestedQuery("orderItems", QueryBuilders.matchQuery("orderItems.spuCode", param.getSpuCode()), ScoreMode.Avg));
        }
        if (StringUtils.hasText(param.getItemName())) {
            String str = esWildcardSpecialCodeTransition(param.getItemName());
            boolQuery.must(QueryBuilders.nestedQuery("orderItems", QueryBuilders.wildcardQuery("orderItems.itemName", "*" + str + "*"), ScoreMode.Avg));
        }
        if (!Objects.isNull(param.getCreateDateStart())) {
            boolQuery.must(QueryBuilders.rangeQuery("createTime").from(DateUtil.getDateTimeStr(param.getCreateDateStart())));
        }
        if (!Objects.isNull(param.getCreateDateEnd())) {
            String dateEnd = DateUtil.getDateTimeStr(DateUtil.getDayTimeEnd(param.getCreateDateEnd()));
            boolQuery.must(QueryBuilders.rangeQuery("createTime").to(dateEnd));
        }
        return boolQuery;
    }

    public static BoolQueryBuilder initExistsInProgressQueryBuilder(String warehouseId) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("warehouseCode", warehouseId));

        // 销售发货单未结束-搜索条件
        BoolQueryBuilder boolDELQuery = QueryBuilders.boolQuery();
        boolDELQuery.must(QueryBuilders.matchQuery("orderType", "01"));
        boolDELQuery.mustNot(QueryBuilders.matchQuery("status", "01"));
        boolDELQuery.mustNot(QueryBuilders.matchQuery("status", "02"));

        // 拒收收货单未结束-搜索条件
        BoolQueryBuilder boolREJQuery = QueryBuilders.boolQuery();
        boolREJQuery.must(QueryBuilders.matchQuery("orderType", "02"));
        boolREJQuery.mustNot(QueryBuilders.matchQuery("status", "05"));

        // 采购收货单未结束-搜索条件
        BoolQueryBuilder boolPURQuery = QueryBuilders.boolQuery();
        boolPURQuery.must(QueryBuilders.matchQuery("orderType", "03"));
        boolPURQuery.mustNot(QueryBuilders.matchQuery("status", "02"));
        boolPURQuery.mustNot(QueryBuilders.matchQuery("status", "01"));

        // 退供发货单未结束-搜索条件
        BoolQueryBuilder boolRTVQuery = QueryBuilders.boolQuery();
        boolRTVQuery.must(QueryBuilders.matchQuery("orderType", "04"));
        boolRTVQuery.mustNot(QueryBuilders.matchQuery("status", "08"));
        boolRTVQuery.mustNot(QueryBuilders.matchQuery("status", "10"));


        BoolQueryBuilder orderQuery = QueryBuilders.boolQuery();
        orderQuery.minimumShouldMatch(1);
        orderQuery.should(boolDELQuery);
        orderQuery.should(boolREJQuery);
        orderQuery.should(boolPURQuery);
        orderQuery.should(boolRTVQuery);
        boolQuery.must(orderQuery);

        return boolQuery;
    }*/

    /**
     * 替换特殊字符
     * @param goodsName 特殊字符
     * @return 转换后字符
     */
    private static String esWildcardSpecialCodeTransition(String goodsName) {
        return goodsName.replace("\\", "\\\\").replace("*", "\\*")
                .replace("?", "\\?")
                .replace(":", "\\:");
    }
}
