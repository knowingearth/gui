package com.y.gui.common.bases;

import lombok.Data;

/**
 * 分页
 */
@Data
public class Page<T> {
    /**
     * 第n页
     */
    private Long page = 1L;

    /**
     * 每页n条
     */
    private Long pageSize = 10L;

    private Long total;

    private T data;

    public Long getStart() {
        return (page - 1) * pageSize;
    }

    public Long getStop() {
        return page * pageSize - 1;
    }

    public Page(Long total, T data) {
        this.total = total;
        this.data = data;
    }

    public Page(Long page, Long pageSize, Long total, T data) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }
}
