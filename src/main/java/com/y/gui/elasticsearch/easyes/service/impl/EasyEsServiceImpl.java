package com.y.gui.elasticsearch.easyes.service.impl;

import com.y.gui.elasticsearch.easyes.dto.EasyESOrderDTO;
import com.y.gui.elasticsearch.easyes.mapper.EasyESOrderMapper;
import com.y.gui.elasticsearch.easyes.param.EasyEsQueryParam;
import com.y.gui.elasticsearch.easyes.service.EasyEsService;
import com.y.gui.vo.PageParentVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Service
public class EasyEsServiceImpl implements EasyEsService {
    // @Resource
    private EasyESOrderMapper esOrderMapper;


    /**
     * 分页查询
     * @param param 分页请求参数
     * @return 查询的分页数据
     */
    @Override
    public PageParentVo<EasyESOrderDTO> queryPageList(EasyEsQueryParam param) {
        /*// 创建查询请求
        SearchRequest searchRequest = new SearchRequest(ESOrderDTO.class.getAnnotation(IndexName.class).value());
        // 创建bool条件集
        BoolQueryBuilder boolQueryBuilder = ESQueryBuilder.initPageQueryBuilder(param);

        SearchSourceBuilder source = searchRequest.source();
        source.query(boolQueryBuilder);

        try {
            source.size(ESOrderDTO.class.getAnnotation(IndexName.class).maxResultWindow());
            SearchResponse countResponse = esOrderMapper.search(searchRequest, RequestOptions.DEFAULT);
            Long total = Arrays.stream(countResponse.getHits().getHits()).count();

            List<ESOrderDTO> datas = new ArrayList<>();
            int pageNum = (param.getPageNum() - 1) * param.getPageSize();
            SortOrder sortOrder = SortOrder.DESC;
            if (SortOrder.ASC.toString().equals(param.getSortType())) {
                sortOrder = SortOrder.ASC;
            }
            source.from(pageNum).size(param.getPageSize()).sort(new FieldSortBuilder("createTime").order(sortOrder));
            SearchResponse response = esOrderMapper.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits().getHits()) {
                String data = hit.getSourceAsString();
                ESOrderDTO ESOrderDTO = JSON.parseObject(data, ESOrderDTO.class);
                datas.add(ESOrderDTO);
            }

            return new PageParentVo<>(param.getPageNum(), param.getPageSize(), total, datas);
        } catch (IOException e) {
            log.info("EsServiceImpl.queryPageList, IOException, param:{}, e:", JSON.toJSONString(param), e);
            throw new RuntimeException("ES查询SCM单据信息失败");
        }*/
        return null;
    }
}
