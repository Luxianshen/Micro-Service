package com.github.lujs.user.api.model;

import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @Describe: 用户实体
 * @Author: lujs
 * @Date: 2019/4/29 10:46
 * @Version: 1.0.0
 **/
@Data
public class User extends BaseEntity<User> {

    private String name;

    private String username;

    private byte[] password;

    private byte[] salt;

    private String phone;

    private String avatar;

    private String avatarId;

    private String email;

    private String sex;

    private String born;

    private String remark;

    private String status;

    private String deptName;

    private String deptId;

    public User() {
        super();
    }

    public User(String id) {
        this.id = id;
    }

    public User(String id,String name) {
        this.id = id;
        this.name = name;
    }

}
