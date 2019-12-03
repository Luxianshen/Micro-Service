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
@TableName("tb_client_api_rel")
public class ClientApiEntity extends BaseEntity {

    private Long clientId;

    private Long apiId;
}
