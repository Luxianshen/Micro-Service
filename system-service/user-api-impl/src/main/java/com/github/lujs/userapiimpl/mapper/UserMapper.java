package com.github.lujs.userapiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.user.api.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户mapper接口
 * @Author: lujs
 * @Data: 2019/5/3 15:15
 * @version: 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
