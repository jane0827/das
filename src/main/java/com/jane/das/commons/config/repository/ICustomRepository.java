package com.jane.das.commons.config.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jane.das.commons.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface ICustomRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * query & copyBean & update
     * @param id 主键
     * @param t 需要更新的对象
     */
    T update(ID id, T t);

    /**
     * 原生sql方式查询
     * @param sql
     * @param params
     * @return
     */
    List<T> nativeQuery(String sql, Map<String, Object> params);

    /**
     * jsonb的类型时候使用
     * @param sql 原生sql
     * @param resultClass 返回的数据类型(该类型中需要注解@TypeDef)
     * @param params 参数
     * @return
     */
    List<?> nativeQuery(String sql, Class<?> resultClass, Map<String, Object> params);

    /**
     * 原生sql方式查询
     * @param sql
     * @param params
     * @return
     */
    List<T> nativeQuery(String sql, Object... params);

    List<T> nativeQuery(String sql, Class<?> resultClass, Object... params);

    /**
     * 原生sql方式查询
     * @param sql
     * @param params
     * @return
     */
    List<?> findBySQL(String sql, Object... params);

    Long countBySQL(String sql);

    Long countBySql(String sql, Map<String, Object> params);

    List<?> findBySQL(String sql, Class<?> resultClass, Object... params);

    List<?> findBySQL(String sql, PageModel pageModel, Object... params);

    /**
     * 原生sql方式查询
     * @param sql
     * @param params
     * @return
     */
    List<T> nativeQuery(String sql, Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 含jsonb的类型分页查询
     * @param sql
     * @param resultClass
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<?> nativeQuery(String sql, Class<?> resultClass, Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 使用原生态sql进行'更新'和'删除'操作
     * @param sql
     * @param params
     * @return
     */
    int updateBySQL(String sql, Map<String, Object> params);

    /**
     * 自定义分页查询
     * @param pageNo 当前第{}页 [pageNo>0]
     * @param pageSize 每页{}条
     * @return
     */
    PageModel findAll(int pageNo, int pageSize);

    /**
     * 自定义分页查询
     * @param spec 查询条件
     * @param pageNo 当前第{}页 [pageNo>0]
     * @param pageSize 每页{}条
     * @return
     */
    PageModel findAll(Specification<T> spec, int pageNo, int pageSize);

    /**
     * 自定义分页查询
     * @param spec 查询条件
     * @param pageNo 当前第{}页 [pageNo>0]
     * @param pageSize 每页{}条
     * @return
     */
    PageModel findAll(Specification<T> spec, int pageNo, int pageSize, Sort sort);

    /**
     * 根据[模板对象]查列表
     * @param t
     * @return
     */
    List<T> findByExample(T t);

    /**
     * 自定义JPQL语句查询
     * @param jpql
     * @return
     */
    List<?> findByJPQL(String jpql);

    List<?> findByJPQL(String jpql, Class<?> resultClass);

    /**
     * 自定义JPQL语句查询
     * @param jpql
     * @return
     */
    List<?> findByJPQL(String jpql, int pageNo, int pageSize);

    /**
     * 自定义count查询
     * @param jpql
     * @return
     */
    Long countByJPQL(String jpql);

    /**
     *使用JPQL进行'更新'和'删除'操作
     *@param sql
     * @param params
     * @return
     */
    int updateByJPQL(String jpql, Map<String, Object> params);

    /**
     * 自定义分页查询
     * @param spec 查询条件
     * @param pageModel
     * @param sort 排序规则
     * @return pageModel 返回的就是参数的pageModel
     */
    PageModel findAll(Specification<T> spec, PageModel pageModel, Sort sort);

    List<?> findByJPQL(String jpql, PageModel pageModel, Map<String, Object> params, Class<?> resultClass);

    List<?> findByJPQL(String jpql, Map<String, Object> params, Class<?> resultClass);

    /**
     * 自定义count查询
     * @param jpql
     * @return
     */
    Long countByJPQL(String jpql, Map<String, Object> params);

    /**
     * 聚合函数使用返回单一结果值
     * @param sql
     * @return
     */
    String aggregateQuery(String sql);
}
