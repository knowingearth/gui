package com.y.gui.elasticsearch.es.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
//@Component
public class EsBasicsUtils {
    /**
     * 官方Client
     */
//    @Resource
//    private RestHighLevelClient esHighLevelClient;


    /**
     * 新增数据 / 覆盖单条原数据
     * 注意:id已存在就是全量覆盖ES中原有的数据，如果没传的值ES会设为空；  ID不存在就是新增数据
     * @param indexName  索引名称
     * @param object   单个数据doc(必须带有@ESId标记主键ID)
     * @return boolean
     */
    /*public boolean indexOrUpdateDoc(String indexName, Object object) {
        log.info("进入ES新增数据/覆盖单条原数据IndexOrUpdateDoc方法");
        if(ObjectUtils.isNotEmpty(object)){
            //需要插入的数据doc
            String source =  JSONObject.toJSONString(object);
            log.info("准备要修改的json数据source：{}",source);
            //判断对象是否存在ID(对象自定义对象属性为ID)
            String ESId=extractFieldsWithESId(object);
            log.info("新增数据/覆盖单条原数据 ESID={}",ESId);
            if(StringUtils.isNotEmpty(ESId)){
                IndexRequest indexRequest = new IndexRequest(indexName).id(ESId);
                indexRequest.source(source, XContentType.JSON);
                try {
                    IndexResponse index = esHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
                    //CREATED=新增成功   OK覆盖成功
                    log.info("执行成功,status="+index.status());
                    return true;
                } catch (Exception e) {
                    log.error("覆盖单条原数据 失败，ESID={}",ESId,e);
                }
            }
            log.info("更新失败失败，ESID={}",ESId);
        }
        return false;
    }*/


    /**
     * 判断传入的对象是否带有 @ESID  存在即使用该字段作为主键，不用es自生成
     */
    private String extractFieldsWithESId(Object object) {
        if (object == null) {
            return null;
        }
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ESID.class)) {
                try {
                    field.setAccessible(true); // 使私有字段可访问
                    return field.get(object).toString();
                } catch (IllegalAccessException e) {
                    log.error("ES判断是否存在@ESID主键异常",e);
                }
            }
        }
        return null;
    }

    /**
     * 对比前后差异，before数据更新后反写入ES
     * @param keyPerfix map记录的key前缀
     * @param map
     * @param before
     * @param after
     */
    /*private void compare(String keyPerfix, Map<String, String> map, Object before, Object after) {
        try {
            for (Field field : before.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object beforeValue = field.get(before);
                Object afterValue = field.get(after);
                if (Objects.isNull(afterValue) || afterValue == beforeValue || afterValue.equals(beforeValue)) {
                    continue;
                }
                if (beforeValue instanceof Collection) {
                    compareItems(keyPerfix, map, (List<ScmOrderItemDTO>) beforeValue, (List<ScmOrderItemDTO>) afterValue);
                    continue;
                }
                field.set(before, afterValue);
                map.put(keyPerfix + field.getName(), beforeValue + "->" + afterValue);
            }
        } catch (Exception e) {
            log.info("[ScmOrderServiceImpl.compare], Exception, e:", e);
        }
    }

    private void compareItems(String keyPerfix, Map<String, String> map, List<ScmOrderItemDTO> befores, List<ScmOrderItemDTO> afters) {
        Map<String, ScmOrderItemDTO> afterMap = afters.stream().collect(Collectors.toMap(ScmOrderItemDTO::getSkuCode, Function.identity(), (v1, v2) -> v1));
        for (ScmOrderItemDTO before : befores) {
            if (!afterMap.containsKey(before.getSkuCode())) {
                continue;
            }
            ScmOrderItemDTO after = afterMap.get(before.getSkuCode());
            keyPerfix += before.getSkuCode() + ContentUtil.DOT;
            compare(keyPerfix, map, before, after);
        }
    }*/
}
