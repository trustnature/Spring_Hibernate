package xyz.tuny.jx.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公共操作父类
 */
public interface GenericDAO<T, ID extends Serializable> {
    T load(ID id);

    T get(ID id);

    List<T> findAll(String hql);

    void persist(T entity);

    Long save(T entity);

    void saveOrUpdate(T entity);

    void delete(ID id);

    void flush();

    public List<T> find(String hql, Object[] param, Integer page, Integer rows);

    public Long count(String hql);

    public Long count(String hql, Object[] param);
}