package com.github.lujs.transmit.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 16:40
 */
@Data
@TableName("tb_role_api_rel")
public class RoleApiEntity extends BaseEntity {

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long roleId;

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long apiId;
}
