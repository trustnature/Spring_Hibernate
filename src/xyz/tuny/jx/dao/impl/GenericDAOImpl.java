package xyz.tuny.jx.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.tuny.jx.dao.GenericDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable>
        implements GenericDAO<T, ID> {
    private static final Logger log = LogManager.getLogger(GenericDAOImpl.class);

    protected final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T load(Long id) {
        return (T) getCurrentSession().load(entityClass, id);
    }

    public T get(Long id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public List<T> findAll(String hql) {
        log.debug(hql);
        return this.getCurrentSession().createQuery(hql).list();
    }

    public void persist(T entity) {
        getCurrentSession().persist(entity);
    }

    public Long save(T entity) {
        return (Long) getCurrentSession().save(entity);
    }

    public void saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Long id) {
        T t = load(id);
        getCurrentSession().delete(t);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        log.debug(hql);
        log.debug(param);
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public Long count(String hql) {
        log.debug(hql);
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

    public Long count(String hql, Object[] param) {
        log.debug(hql);
        log.debug(param);
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return (Long) q.uniqueResult();
    }
}
