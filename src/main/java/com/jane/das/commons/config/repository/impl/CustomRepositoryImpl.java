package com.jane.das.commons.config.repository.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jane.das.commons.model.PageModel;
import com.jane.das.commons.config.repository.ICustomRepository;
import com.jane.das.commons.util.CopyBeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements ICustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public T update(ID id, T t) {
        T source = super.findOne(id);
        CopyBeanUtils.copyProperties(t, source);
        return super.save(source);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> nativeQuery(String sql, Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        return query.getResultList();
    }

    @Override
    public List<?> nativeQuery(String sql, Class<?> resultClass, Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql, resultClass);
        setParams(query, params);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> nativeQuery(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> nativeQuery(String sql, Class<?> resultClass, Object... params) {
        Query query = entityManager.createNativeQuery(sql, resultClass);
        setParams(query, params);
        return query.getResultList();
    }

    @Override
    public List<?> findBySQL(String sql, Class<?> resultClass, Object... params) {
        Query query = entityManager.createNativeQuery(sql, resultClass);
        setParams(query, params);
        return query.getResultList();
    }

    /**
     * 设置query参数
     *
     * @param query
     * @param params
     */
    private void setParams(Query query, Object... params) {
        if (params != null && params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter((i + 1), params[i]);
            }
        }
    }

    /**
     * 设置query参数
     *
     * @param query
     * @param params
     */
    private void setParams(Query query, Map<String, Object> params) {
        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String string : keySet) {
                Object obj = params.get(string);
                query.setParameter(string, obj);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> nativeQuery(String sql, Map<String, Object> params, int pageNo, int pageSize) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<?> nativeQuery(String sql, Class<?> resultClass, Map<String, Object> params, int pageNo, int pageSize) {
        Query query = entityManager.createNativeQuery(sql, resultClass);
        setParams(query, params);
        query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int updateBySQL(String sql, Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        return query.executeUpdate();
    }

    @Override
    public PageModel findAll(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo - 1, pageSize);
        PageModel pageModel = new PageModel();
        Page<T> page = findAll(pageable);
        pageModel.setCount(page.getTotalElements());
        pageModel.setCurrentNo(pageNo);
        pageModel.setObject(page.getContent());
        pageModel.setTotalPages(page.getTotalPages());
        return pageModel;
    }

    @Override
    public List<T> findByExample(T t) {
        Example<T> example = Example.of(t);
        return findAll(example);
    }

    @Override
    public PageModel findAll(Specification<T> spec, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo - 1, pageSize);
        PageModel pageModel = new PageModel();
        Page<T> page = findAll(spec, pageable);
        pageModel.setCount(page.getTotalElements());
        pageModel.setCurrentNo(pageNo);
        pageModel.setObject(page.getContent());
        pageModel.setTotalPages(page.getTotalPages());
        return pageModel;
    }

    @Override
    public PageModel findAll(Specification<T> spec, int pageNo, int pageSize, Sort sort) {
        Pageable pageable = new PageRequest(pageNo - 1, pageSize, sort);
        PageModel pageModel = new PageModel();
        Page<T> page = findAll(spec, pageable);
        pageModel.setCount(page.getTotalElements());
        pageModel.setCurrentNo(pageNo);
        pageModel.setObject(page.getContent());
        pageModel.setTotalPages(page.getTotalPages());
        return pageModel;
    }

    @Override
    public List<?> findByJPQL(String jpql) {
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<?> findByJPQL(String jpql, Class<?> resultClass) {
        Query query = entityManager.createQuery(jpql, resultClass);
        return query.getResultList();
    }

    @Override
    public List<?> findByJPQL(String jpql, int pageNo, int pageSize) {
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Long countByJPQL(String jpql) {
        Query query = entityManager.createQuery(jpql);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<?> findBySQL(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        return query.getResultList();
    }

    @Override
    public Long countBySQL(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> params){
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        Object count = query.getSingleResult();
        if(count instanceof BigInteger){
            return ((BigInteger) count).longValue();
        }
        return (Long)query.getSingleResult();
    }

    @Override
    public List<?> findBySQL(String sql, PageModel pageModel, Object... params) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, params);
        query.setFirstResult((pageModel.getCurrentNo() - 1) * pageModel.getPageSize());
        query.setMaxResults(pageModel.getPageSize());
        return query.getResultList();
    }

    @Override
    public int updateByJPQL(String jpql, Map<String, Object> params) {
        Query query = entityManager.createQuery(jpql);
        setParams(query, params);
        return query.executeUpdate();
    }

    @Override
    public PageModel findAll(Specification<T> spec, PageModel pageModel, Sort sort) {
        Pageable pageable = new PageRequest(pageModel.getCurrentNo() - 1, pageModel.getPageSize(), sort);
        Page<T> page = findAll(spec, pageable);
        pageModel.setCount(page.getTotalElements());
        pageModel.setObject(page.getContent());
        pageModel.setTotalPages(page.getTotalPages());
        return pageModel;
    }

    @Override
    public List<?> findByJPQL(String jpql, PageModel pageModel, Map<String, Object> params, Class<?> resultClass) {
        Query query = entityManager.createQuery(jpql, resultClass);
        setParams(query, params);
        query.setFirstResult((pageModel.getCurrentNo() - 1) * pageModel.getPageSize());
        query.setMaxResults(pageModel.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<?> findByJPQL(String jpql, Map<String, Object> params, Class<?> resultClass) {
        Query query = entityManager.createQuery(jpql, resultClass);
        setParams(query, params);
        return query.getResultList();
    }

    @Override
    public Long countByJPQL(String jpql, Map<String, Object> params) {
        Query query = entityManager.createQuery(jpql);
        setParams(query, params);
        return (Long) query.getSingleResult();
    }

    @Override
    public String aggregateQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return String.valueOf(query.getSingleResult());
    }

}
