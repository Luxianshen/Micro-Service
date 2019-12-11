package com.github.lujs.auth.apiimpl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lujs.auth.api.model.Menu.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 菜单mapper
 * @Author: lujs
 * @Data: 2019/5/18 10:32
 * @version: 1.0.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
