package com.y.gui.common.utils.file;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Consumer2 extends ConsumerParent {
    private Integer type = DataTypeEnum.TJ.getType();

    public void onMessage(String data) throws Exception {
        if (!StringUtils.hasText(data)) {
            return;
        }

        doMessage(type, data);
    }
}
