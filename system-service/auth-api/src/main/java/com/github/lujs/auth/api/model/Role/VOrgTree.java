package com.github.lujs.auth.api.model.Role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Lujs
 * @date: 2019/2/12
 * @desc: v-org-tree 组件封装
 */
@Data
public class VOrgTree implements Serializable {

    /**
     * 节点id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否已勾选
     */
    private Boolean checked;

    /**
     * 上级节点id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;

    /**
     * 是否展开子树
     */
    private Boolean expand = true;

    /**
     * 子树节点
     */
    List<VOrgTree> children;
}
