package com.github.lujs.persistence;

import java.util.List;

/**
 * @Description: Crud Mapper
 * @Author: lujs
 * @Data: 2019/5/3 15:17
 * @version: 1.0.0
 */

public interface CrudMapper<T> extends BaseMapper {

    /**
     * 获取单条数据
     *
     * @param entity entity
     * @return T
     */
    T get(T entity);

    /**
     * 获取列表数据
     *
     * @param entity entity
     * @return List
     */
    List<T> findList(T entity);

    /**
     * 获取全部列表数据
     *
     * @param entity entity
     * @return List
     */
    List<T> findAllList(T entity);

    /**
     * 根据id获取列表数据
     *
     * @param entity entity
     * @return List
     */
    List<T> findListById(T entity);

    /**
     * 获取所有数据
     *
     * @return List
     */
    List<T> findAllList();

    /**
     * 插入单条数据
     *
     * @param entity entity
     * @return int
     */
    int insert(T entity);

    /**
     * 更新单条数据
     *
     * @param entity entity
     * @return int
     */
    int update(T entity);

    /**
     * 删除单条数据
     *
     * @param entity entity
     * @return int
     */
    int delete(T entity);

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     */
    int deleteAll(String[] ids);
}

