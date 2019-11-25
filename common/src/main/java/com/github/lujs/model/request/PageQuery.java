package com.github.lujs.model.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.validation.Valid;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/25 14:55
 */
public class PageQuery<T, P> extends Page<T> {
    @Valid
    private P params;

    public PageQuery() {
    }

    public void setParams(P params) {
        this.params = params;
    }

    public P getParams() {
        return this.params;
    }
}
