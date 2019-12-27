package com.github.lujs.user.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/27 14:31
 */
@Data
public class UserClientQuery implements Serializable {

    private String agentId;

    private Integer state;
}
