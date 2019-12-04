package com.github.lujs.transmit.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.lujs.persistence.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/21 10:46
 */
@Data
public class ApiEntityDto extends BaseEntity {

    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long id;

    private String name;

    private Boolean checked;
}
