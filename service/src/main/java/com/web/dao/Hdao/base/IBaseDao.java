package com.web.dao.Hdao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.util.model.PageInfo;
import com.util.model.QueryRule;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;


public interface IBaseDao<T extends Serializable, PK extends Serializable> {

	/**
	 * 
	 * 保存PO
	 */

	public abstract PK save(T entity);

	/**
	 * 
	 * 保存或更新PO
	 */

	public abstract void saveOrUpdate(T entity);

	/**
	 * 
	 * 更新PO
	 */

	public abstract void update(T entity);

	/**
	 * 
	 * 合并PO
	 */

	public abstract void merge(T entity);

	/**
	 * 
	 * 根据id删除PO
	 */

	public abstract void delete(PK id);

	/**
	 * 根据ID批量删除
	 * @param ids
	 */
	public abstract void deleteByPks(Collection<PK> ids);

	/**
	 * 
	 * 删除PO
	 */

	public abstract void deleteObject(T entity);

	/**
	 * 
	 * 根据id判断PO是否存在
	 */

	public abstract boolean exists(PK id);

	/**
	 * 
	 * 根据id加载PO
	 */

	public abstract T load(PK id);

	/**
	 * 
	 * 根据id获取PO
	 */

	public abstract T get(PK id);

	/**
	 * 
	 * 获取PO总数(默认为entityClass)
	 */

	public abstract int countAll();

	/**
	 * 
	 * 根据Criteria查询条件，获取PO总数
	 */

	public abstract int countAll(Criteria criteria);

	/**
	 * 
	 * 删除所有
	 */

	public abstract void deleteAll(Collection<?> entities);

	/**
	 * 批量保存
	 * @param entities
	 */

	public abstract void saveAll(Collection<?> entities);

	/**
	 * 
	 * 获取全部对象
	 */

	public abstract List<T> list();

	/**
	 * 
	 * 获取对象列表根据Criteria
	 */

	public abstract List<T> list(Criteria criteria);

	/**
	 * 
	 * 离线查询
	 */

	public abstract <T> List<T> list(DetachedCriteria criteria);

	/**
	 * 
	 * 获取全部对象，支持排序
	 * 
	 * 
	 * 
	 * @param orderBy
	 * 
	 * 
	 * 
	 * @param isAsc
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract List<T> list(String orderBy, boolean isAsc);

	/**
	 * 
	 * 按属性查找对象列表，匹配方式为相等
	 * 
	 * 
	 * 
	 * @param propertyName
	 * 
	 * 
	 * 
	 * @param value
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract List<T> list(String propertyName, Object value);

	/**
	 * 
	 * 按Criteria查询对象列表
	 * 
	 * 
	 * 
	 * @param
	 *
	 * 
	 * @param criterions
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract List<T> list(Criterion... criterions);

	/**
	 * 
	 * 按属性查找唯一对象，匹配方式为相等
	 * 
	 * 
	 * 
	 * @param propertyName
	 * 
	 * 
	 * 
	 * @param value
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract T uniqueResult(String propertyName, Object value);

	/**
	 * 
	 * 按Criteria查询唯一对象
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @param criterions
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract T uniqueResult(Criterion... criterions);

	/**
	 * 
	 * 按Criteria查询唯一对象
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract T uniqueResult(Criteria criteria);

	/**
	 * 
	 * 为Criteria添加distinct transformer
	 * 
	 * 
	 * 
	 * @param criteria
	 * 
	 * 
	 * 
	 * @return
	 */

	// 认为没用

	public abstract Criteria distinct(Criteria criteria);

	/**
	 * 
	 * 强制清空session
	 */

	public abstract void flush();

	/**
	 * 
	 * 清空session
	 */

	public abstract void clear();

	public abstract Criteria createCriteria();

	/**
	 * 
	 * 根据Criterion条件创建Criteria
	 * 
	 * 
	 * 
	 * @param
	 */

	public abstract Criteria createCriteria(Criterion... criterions);

	/**
	 * 
	 * 分页查询Criteria
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @return
	 */

	public abstract List<T> findPage(Criteria criteria, int pageNo, int pageSize);

	/**
	 * 封装Rule,方式获取数据。
	 * @param queryRule
	 * @return
	 */
	List<T> find(QueryRule queryRule);

    /**
     * 根据HQL获取实体列表，主要用于查询。
     */
    List<T> findByHql(String hql ,Object[] values);

    /**
     *执行hql语句，主要用于修改，删除等操作。
     * @param hql  hql 语句  ，例如：delete from User  where userName = ? and status =?<br/>
     * @param values  sql 条件值  例如：new Object[] { userName, status} <br/>
     */
     int executeHql(String hql ,Object[] values);


    /**
     * QueryRule 分页
     * @param queryRule
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<T> getPagenInfo(QueryRule queryRule ,int pageNo , int pageSize);

}