package com.github.lujs.transmit.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 9:42
 */
@Data
public class User implements Serializable {
    private String name;
    private String pwd;
}
