package com.github.lujs.auth.api.model.Menu;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 菜单实体
 * @Author: lujs
 * @Data: 2019/5/18 9:47
 * @version: 1.0.0
 */
@Data
public class Menu extends BaseEntity<Menu> {

    public Menu(String id){
        this.id = id;
    }
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String permission;

    /**
     * url
     */
    private String url;

    /**
     * 重定向url
     */
    private String redirect;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号
     */
    private String sort;

    /**
     * 类型
     */
    private String type;

    /**
     * 模块
     */
    private String component;

    /**
     * 路径
     */
    private String path;

    /**
     * 备注
     */
    private String remark;

}
