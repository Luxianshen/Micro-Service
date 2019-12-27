package com.github.lujs.auth.api.model.Menu;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/27 11:52
 */
@Data
public class MenuQuery implements Serializable {

    private String name;

    private String permissionCode;
}
