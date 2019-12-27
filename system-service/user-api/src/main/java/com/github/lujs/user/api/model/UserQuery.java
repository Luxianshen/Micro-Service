package com.github.lujs.user.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/27 11:58
 */
@Data
public class UserQuery implements Serializable {

    private String name;

    private String agentId;

    private Integer state;

    private String phoneNo;
}
