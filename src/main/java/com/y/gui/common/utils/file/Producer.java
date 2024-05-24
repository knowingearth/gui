package com.y.gui.common.utils.file;

import com.y.gui.common.extension.RedisExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    private RedisExt redisExt;

    public void testSend() {
        for (DataTypeEnum value : DataTypeEnum.values()) {
            for (int i = 0; i < 10; i++) {
                Integer type = value.getType();
                String data = type + ":" + i;// todo 去es取详情

                // todo 发送kafka生产数据

                // 更新完成标识的缓存过期时间为10分钟
                String finishKey = Content.FINISHED_KEY + type;
                redisExt.set(finishKey, 0, Content.TEN);
            }
        }
    }
}
