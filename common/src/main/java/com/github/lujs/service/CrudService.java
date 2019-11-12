package com.github.lujs.service;

import com.github.lujs.persistence.BaseEntity;
import com.github.lujs.utils.ReflectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: service基类
 * @Author: lujs
 * @Data: 2019/5/3 15:17
 * @version: 1.0.0
 */
public abstract class CrudService<D extends CrudMapper<T>, T extends BaseEntity<T>> extends BaseService {

    @Autowired
    protected D dao;

    /**
     * 根据id获取
     *
     * @param id id
     * @return T
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T get(String id) throws Exception {
        Class<T> entityClass = ReflectionUtil.getClassGenricType(getClass(), 1);
        T entity = entityClass.getConstructor(String.class).newInstance("0");
        entity.setId(id);
        return dao.get(entity);
    }

    /**
     * 获取单条数据
     *
     * @param entity entity
     * @return T
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表
     *
     * @param entity entity
     * @return List
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询列表
     *
     * @param entity entity
     * @return List
     */
    public List<T> findAllList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询列表
     *
     * @param entity entity
     * @return List
     */
    public List<T> findListById(T entity) {
        if (entity.getIds() == null || entity.getIds().length == 0)
            return new ArrayList<>();
        return dao.findListById(entity);
    }

    /**
     * 查询分页
     *
     * @param page   page
     * @param entity entity
     * @return PageInfo
     */
    public PageInfo<T> findPage(PageInfo<T> page, T entity) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<T>(dao.findList(entity));
    }

    /**
     * 插入或更新
     *
     * @param entity entity
     * @return int
     */
    @Transactional
    public int save(T entity) {
        if (entity.isNewRecord()) {
            return dao.insert(entity);
        } else {
            return dao.update(entity);
        }
    }

    /**
     * 删除
     *
     * @param entity entity
     * @return int
     */
    @Transactional
    public int delete(T entity) {
        return dao.delete(entity);
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     */
    @Transactional
    public int deleteAll(String[] ids) {
        return this.dao.deleteAll(ids);
    }

    /**
     * 插入数据
     *
     * @param entity entity
     * @return int
     */
    @Transactional
    public int insert(T entity) {
        return dao.insert(entity);
    }

    /**
     * 更新数据
     *
     * @param entity entity
     * @return int
     */
    @Transactional
    public int update(T entity) {
        return dao.update(entity);
    }
}

