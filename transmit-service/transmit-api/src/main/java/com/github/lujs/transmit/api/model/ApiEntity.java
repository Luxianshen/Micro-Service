package com.github.lujs.transmit.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description: 接口实体
 * @Author lujs
 * @Date 2019/11/25 16:48
 */
@Data
@TableName("tb_api")
public class ApiEntity extends BaseEntity {

    /**
     * pid
     */
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long pid;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 请求键
     */
    private String apiKey;
    /**
     * 真实调用的url
     */
    private String realUrl;
    /**
     * 请求方式
     */
    private String reqType;
    /**
     * 接口状态
     */
    private Integer state;
    /**
     * 提供系统
     */
    private String sysId;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 权限编码
     */
    private String permissionCode;
    /**
     * 接口描述
     */
    private String apiDesc;
}
