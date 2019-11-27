package com.github.lujs.auth.api.model.Menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description: 菜单实体
 * @Author: lujs
 * @Data: 2019/5/18 9:47
 * @version: 1.0.0
 */
@Data
@TableName("tb_menu")
public class Menu extends BaseEntity {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限名称
     */
    private String label;

    /**
     * 权限状态
     */
    private Integer state;

    /**
     * 上级权限id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 权限路径
     */
    private String path;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限资源图标
     */
    private String icon;

    /**
     * 权限资源描述
     */
    private String permissionDesc;

    /**
     * 组成
     */
    private String component;

    /**
     * 所属系统
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sysId;

    private String redirect;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;

}
