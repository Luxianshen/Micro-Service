package com.github.lujs.persistence;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/18 16:43
 */
@Data
public class BaseEntity implements Serializable {

    @TableId
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer deleted;

    public void init() {
        this.createTime = new Date();
        this.deleted = 0;
    }

    public BaseEntity() {
    }

    @Override
    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", deleted=" + this.getDeleted() + ")";
    }
}
