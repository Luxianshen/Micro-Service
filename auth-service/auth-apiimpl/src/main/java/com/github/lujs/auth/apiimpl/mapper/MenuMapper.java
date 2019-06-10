package com.github.lujs.auth.apiimpl.mapper;

import com.github.lujs.auth.api.model.Menu.Menu;
import com.github.lujs.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜单mapper
 * @Author: lujs
 * @Data: 2019/5/18 10:32
 * @version: 1.0.0
 */
@Mapper
public interface MenuMapper extends CrudMapper<Menu> {

    List<Menu> findByRole(String role);
}
