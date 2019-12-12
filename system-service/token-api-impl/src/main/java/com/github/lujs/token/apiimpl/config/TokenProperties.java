package com.github.lujs.token.apiimpl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/12 12:01
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "valid")
public class TokenProperties {

    private Integer tokenTime;

    private Boolean validFlag;

    private Integer validTime;
}
