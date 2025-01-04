package com.y.gui.elasticsearch.es.builder;

public class TaskBoxEsBuilder {
    /**
     * 组装查询条件
     * @param param 页面查询条件
     * @param userId 当前登陆用户ID
     * @return ES查询条件
     */
    /*public static BoolQueryBuilder generateBoolQueryBuilder(TaskBoxPageParam param, List<String> taskKeys, String userId) {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        // 查询我的任务
        if (Objects.nonNull(param.getQueryMineTask()) && param.getQueryMineTask()) {
            queryBuilder.must(QueryBuilders.termQuery("assignee", userId));
        }

        // 任务ID
        if (StringUtils.hasLength(param.getTaskId())) {
            queryBuilder.must(QueryBuilders.termQuery("taskId", param.getTaskId()));
        }

        // 任务KEY
        if (!CollectionUtils.isEmpty(taskKeys)) {
            queryBuilder.must(QueryBuilders.termsQuery("taskKey", taskKeys));
        }

        // 关联的单据ID
        String relevanceOrderId = param.getRelevanceOrderId();
        if (StringUtils.hasLength(relevanceOrderId)) {
            BoolQueryBuilder orderQuery = QueryBuilders.boolQuery();
            orderQuery.minimumShouldMatch(1);

            BoolQueryBuilder orderIdQuery = QueryBuilders.boolQuery();
            orderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.orderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(orderIdQuery);

            BoolQueryBuilder offSaleOrderIdQuery = QueryBuilders.boolQuery();
            offSaleOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.offSaleOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(offSaleOrderIdQuery);

            BoolQueryBuilder delOrderIdQuery = QueryBuilders.boolQuery();
            delOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.delOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(delOrderIdQuery);

            BoolQueryBuilder rtnOrderIdQuery = QueryBuilders.boolQuery();
            rtnOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.rtnOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(rtnOrderIdQuery);

            BoolQueryBuilder expressOrderIdQuery = QueryBuilders.boolQuery();
            expressOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.expressOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(expressOrderIdQuery);

            BoolQueryBuilder purOrderIdQuery = QueryBuilders.boolQuery();
            purOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.purOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(purOrderIdQuery);

            BoolQueryBuilder preOrderIdQuery = QueryBuilders.boolQuery();
            preOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.preOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(preOrderIdQuery);

            BoolQueryBuilder prtvOrderIdQuery = QueryBuilders.boolQuery();
            prtvOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.prtvOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(prtvOrderIdQuery);

            BoolQueryBuilder rtvOrderIdQuery = QueryBuilders.boolQuery();
            rtvOrderIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.rtvOrderId", relevanceOrderId), ScoreMode.Avg));
            orderQuery.should(rtvOrderIdQuery);

            queryBuilder.must(orderQuery);
        }

        // 促销活动
        if (StringUtils.hasLength(param.getPromotion())) {
            BoolQueryBuilder promotionQuery = QueryBuilders.boolQuery();
            promotionQuery.minimumShouldMatch(1);

            BoolQueryBuilder promotionIdQuery = QueryBuilders.boolQuery();
            promotionIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.promotionId", param.getPromotion()), ScoreMode.Avg));
            promotionQuery.should(promotionIdQuery);

            BoolQueryBuilder promotionNameQuery = QueryBuilders.boolQuery();
            promotionNameQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.matchQuery("tAbstract.promotionName", param.getPromotion()), ScoreMode.Avg));
            promotionQuery.should(promotionNameQuery);

            queryBuilder.must(promotionQuery);
        }

        // 商品
        if (StringUtils.hasLength(param.getGoods())) {
            BoolQueryBuilder goodsQuery = QueryBuilders.boolQuery();
            goodsQuery.minimumShouldMatch(1);

            BoolQueryBuilder skuIdQuery = QueryBuilders.boolQuery();
            skuIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.skuId", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(skuIdQuery);

            BoolQueryBuilder goodsNoQuery = QueryBuilders.boolQuery();
            goodsNoQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.goodsNo", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(goodsNoQuery);

            BoolQueryBuilder goodsNameQuery = QueryBuilders.boolQuery();
            goodsNameQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.matchQuery("tAbstract.goodsName", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(goodsNameQuery);

            BoolQueryBuilder _skuIdQuery = QueryBuilders.boolQuery();
            _skuIdQuery.must(QueryBuilders.nestedQuery("tAbstract.skus", QueryBuilders.termQuery("tAbstract.skus.skuId", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(_skuIdQuery);

            BoolQueryBuilder _goodsNoQuery = QueryBuilders.boolQuery();
            _goodsNoQuery.must(QueryBuilders.nestedQuery("tAbstract.skus", QueryBuilders.termQuery("tAbstract.skus.goodsNo", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(_goodsNoQuery);

            BoolQueryBuilder _goodsNameQuery = QueryBuilders.boolQuery();
            _goodsNameQuery.must(QueryBuilders.nestedQuery("tAbstract.skus", QueryBuilders.matchQuery("tAbstract.skus.goodsName", param.getGoods()), ScoreMode.Avg));
            goodsQuery.should(_goodsNameQuery);

            queryBuilder.must(goodsQuery);
        }

        // 供货商
        if (StringUtils.hasLength(param.getSupplier())) {
            BoolQueryBuilder suppliseQuery = QueryBuilders.boolQuery();
            suppliseQuery.minimumShouldMatch(1);

            BoolQueryBuilder supplierIdQuery = QueryBuilders.boolQuery();
            supplierIdQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.termQuery("tAbstract.supplierId", param.getSupplier()), ScoreMode.Avg));
            suppliseQuery.should(supplierIdQuery);

            BoolQueryBuilder supplierNameQuery = QueryBuilders.boolQuery();
            supplierNameQuery.must(QueryBuilders.nestedQuery("tAbstract", QueryBuilders.matchQuery("tAbstract.supplierName", param.getSupplier()), ScoreMode.Avg));
            suppliseQuery.should(supplierNameQuery);

            BoolQueryBuilder _supplierIdQuery = QueryBuilders.boolQuery();
            _supplierIdQuery.must(QueryBuilders.nestedQuery("tAbstract.skus", QueryBuilders.termQuery("tAbstract.skus.supplierId", param.getSupplier()), ScoreMode.Avg));
            suppliseQuery.should(_supplierIdQuery);

            BoolQueryBuilder _supplierNameQuery = QueryBuilders.boolQuery();
            _supplierNameQuery.must(QueryBuilders.nestedQuery("tAbstract.skus", QueryBuilders.matchQuery("tAbstract.skus.supplierName", param.getSupplier()), ScoreMode.Avg));
            suppliseQuery.should(_supplierNameQuery);


            queryBuilder.must(suppliseQuery);
        }

        // 任务Key
        if (StringUtils.hasLength(param.getTaskKey())) {
            queryBuilder.must(QueryBuilders.termQuery("taskKey", param.getTaskKey()));
        }

        // 任务名称
        if (StringUtils.hasLength(param.getTaskName())) {
            queryBuilder.must(QueryBuilders.termQuery("taskName", param.getTaskName()));
        }

        // 所属系统
        if (Objects.nonNull(param.getSysTag())) {
            queryBuilder.must(QueryBuilders.termQuery("sysTag", param.getSysTag().name()));
        }

        // 状态
        if (StringUtils.hasLength(param.getTaskStatus())) {
            queryBuilder.must(QueryBuilders.termQuery("taskStatus", param.getTaskStatus()));
        }

        // 任务创建的时间开始
        if (Objects.nonNull(param.getTaskCreateTimeStart())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskCreateTime").from(DateUtil.getDateTimeStr(param.getTaskCreateTimeStart())));
        }
        // 任务创建的时间结束
        if (Objects.nonNull(param.getTaskCreateTimeEnd())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskCreateTime").to(DateUtil.getDateTimeStr(param.getTaskCreateTimeEnd())));
        }

        // 任务被接收的时间开始
        if (Objects.nonNull(param.getTaskAssignTimeStart())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskAssignTime").from(DateUtil.getDateTimeStr(param.getTaskAssignTimeStart())));
        }
        // 任务被接收的时间结束
        if (Objects.nonNull(param.getTaskAssignTimeEnd())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskAssignTime").to(DateUtil.getDateTimeStr(param.getTaskAssignTimeEnd())));
        }

        // 任务完成时间开始
        if (Objects.nonNull(param.getTaskCompleteTimeStart())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskCompleteTime").from(DateUtil.getDateTimeStr(param.getTaskCompleteTimeStart())));
        }
        // 任务完成时间结束
        if (Objects.nonNull(param.getTaskCompleteTimeEnd())) {
            queryBuilder.must(QueryBuilders.rangeQuery("taskCompleteTime").to(DateUtil.getDateTimeStr(param.getTaskCompleteTimeEnd())));
        }

        return queryBuilder;
    }*/
}
