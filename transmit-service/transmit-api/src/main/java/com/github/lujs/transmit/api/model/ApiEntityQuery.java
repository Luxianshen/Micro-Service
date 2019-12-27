package com.github.lujs.transmit.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/27 11:42
 */
@Data
public class ApiEntityQuery implements Serializable {

    private String name;

    private String apiKey;
}
