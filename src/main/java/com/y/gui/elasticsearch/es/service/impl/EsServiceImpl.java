package com.y.gui.elasticsearch.es.service.impl;

import com.y.gui.elasticsearch.es.dto.TaskBoxDTO;
import com.y.gui.elasticsearch.es.param.TaskBoxPageParam;
import com.y.gui.elasticsearch.es.service.EsService;
import com.y.gui.elasticsearch.es.vo.TaskBoxPageVO;
import com.y.gui.vo.PageParentVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
public class EsServiceImpl implements EsService {
    private final String INDEX = "task_box";

//    @Resource
//    private RestHighLevelClient restHighLevelClient;

    /**
     * 分页查询任务箱
     * @param param 查询条件
     * @return 任务箱数据
     */
    @Override
    public PageParentVo<TaskBoxPageVO> queryTaskBoxPage(TaskBoxPageParam param, String userId) {
        /*try {
            log.info("TaskBoxServiceImpl.queryTaskBoxPage, userId:{}, param:{}", userId, JSON.toJSONString(param));
            List<String> viewTaskKeys = new ArrayList<>(), dealTaskKeys = new ArrayList<>();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            // 添加分页参数（从0开始计数）
            searchSourceBuilder.from((param.getPageNum() - 1) * param.getPageSize());
            searchSourceBuilder.size(param.getPageSize());
            // 排序字段
            if (StringUtils.hasLength(param.getSortColumn())) {
                searchSourceBuilder.sort(param.getSortColumn(), SortOrder.DESC);
            } else {
                searchSourceBuilder.sort("taskCreateTime", SortOrder.DESC);
            }
            // 获取匹配查询的文档总数(不是分页的结果数)
            searchSourceBuilder.trackTotalHits(true);

            // 组装查询条件
            searchSourceBuilder.query(TaskBoxEsBuilder.generateBoolQueryBuilder(param, viewTaskKeys, userId));

            // 构建搜索请求
            SearchRequest searchRequest = new SearchRequest(INDEX);
            searchRequest.source(searchSourceBuilder);

            // 搜索
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            // 匹配到的文档总数
            long total = searchResponse.getHits().getTotalHits().value;
            log.info("TaskBoxServiceImpl.queryTaskBoxPage, total:{}", total);

            // 当前页搜索结果
            List<TaskBoxPageVO> datas = new ArrayList<>();
            for (SearchHit hit : searchResponse.getHits()) {
                TaskBoxPageVO dto = JSON.parseObject(hit.getSourceAsString(), TaskBoxPageVO.class);
                datas.add(dto);
            }

            // 返回结果
            return new PageParentVo<>(param.getPageNum(), param.getPageSize(), total, datas);
        } catch (IOException e) {
            log.info("TaskBoxServiceImpl.queryTaskBoxPage, IOException, e:", e);
            throw new RuntimeException("ES查询SCM单据信息失败");
        }*/
        return null;
    }

    /**
     * 获取任务详情
     * @param taskId 任务ID
     * @return 任务详情
     */
    @Override
    public TaskBoxDTO queryTaskBoxInfo(String taskId) {
        /*try {
            BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
            if (StringUtils.hasText(taskId)) {
                queryBuilder.must(QueryBuilders.termQuery("taskId", taskId));
            }


            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(queryBuilder);

            // 构建搜索请求
            SearchRequest searchRequest = new SearchRequest(INDEX);
            searchRequest.source(searchSourceBuilder);

            // 搜索
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            long total = searchResponse.getHits().getTotalHits().value;
            if (0 >= total) {
                return null;
            }
            return JSON.parseObject(searchResponse.getHits().getAt(0).getSourceAsString(), TaskBoxDTO.class);
        } catch (IOException e) {
            return null;
        }*/
        return null;
    }
}
