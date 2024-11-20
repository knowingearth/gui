package com.y.gui.common.event.publish;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PushTestEvent extends ApplicationEvent {
    private Integer id;
    private String name;
    private Object data;

    public PushTestEvent(Object source) {
        super(source);
    }
}
