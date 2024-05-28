package com.y.gui.common.utils.file;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Consumer3 extends ConsumerParent {

    private Integer type = DataTypeEnum.HB.getType();

    public void onMessage(String data) throws Exception {
        if (!StringUtils.hasText(data)) {
            return;
        }

        doMessage(type, data);
    }
}
