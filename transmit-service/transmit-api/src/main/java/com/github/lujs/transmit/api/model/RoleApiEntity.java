package com.github.lujs.transmit.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
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

    private Long roleId;

    private Long apiId;
}
