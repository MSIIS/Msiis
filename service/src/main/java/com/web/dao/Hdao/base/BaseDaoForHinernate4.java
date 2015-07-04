package com.web.dao.Hdao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.util.model.QueryRule;
import com.util.tools.QueryRuleUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;


public class BaseDaoForHinernate4<T extends Serializable, PK extends Serializable> implements  IBaseDao<T, PK>{

	// 日志输出类

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(BaseDaoForHinernate4.class);

	// 泛型反射类

	protected Class<T> entityClass;
	
	
	// 通过反射获取子类确定的泛型类

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDaoForHinernate4() {

		Type genType = getClass().getGenericSuperclass();

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		entityClass = (Class) params[0];

	}

	/**
	 * 
	 * 注入sessionFactory
	 */

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {

		// 事务必须是开启的(Required)，否则获取不到

		return sessionFactory.getCurrentSession();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#save(T)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public PK save(T entity) {

		return (PK) getSession().save(entity);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#saveOrUpdate(T)
	 */

	@Override
	public void saveOrUpdate(T entity) {

		getSession().saveOrUpdate(entity);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#update(T)
	 */

	@Override
	public void update(T entity) {

		getSession().update(entity);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#merge(T)
	 */

	@Override
	public void merge(T entity) {

		getSession().merge(entity);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#delete(PK)
	 */

	@Override
	public void delete(PK id) {

		getSession().delete(this.get(id));

	}
	
	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#deleteByPks(java.util.Collection)
	 */
	@Override
	public void deleteByPks(Collection<PK>  ids){
		if(!CollectionUtils.isEmpty(ids)){
			for(PK pk :ids){
				getSession().delete(this.get(pk));
			}


		}
		
	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#deleteObject(T)
	 */

	@Override
	public void deleteObject(T entity) {

		getSession().delete(entity);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#exists(PK)
	 */

	@Override
	public boolean exists(PK id) {

		return get(id) != null;

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#load(PK)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public T load(PK id) {

		return (T) getSession().load(this.entityClass, id);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#get(PK)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public T get(PK id) {

		return (T) getSession().get(this.entityClass, id);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#countAll()
	 */

	@Override
	public int countAll() {

		Criteria criteria = createCriteria();

		return Integer.valueOf(criteria.setProjection(Projections.rowCount())

		.uniqueResult().toString());

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#countAll(org.hibernate.Criteria)
	 */

	@Override
	public int countAll(Criteria criteria) {

		return Integer.valueOf(criteria.setProjection(Projections.rowCount())

		.uniqueResult().toString());

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#deleteAll(java.util.Collection)
	 */

	@Override
	public void deleteAll(Collection<?> entities) {

		if (entities == null)

			return;

		for (Object entity : entities) {

			getSession().delete(entity);

		}

	}
	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#saveAll(java.util.Collection)
	 */
	
	@Override
	public void saveAll(Collection<?> entities){
		if(entities==null){
			return;
		}
		for(Object entity :entities){
			getSession().saveOrUpdate(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list()
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list() {

		return createCriteria().list();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list(org.hibernate.Criteria)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(Criteria criteria) {

		return criteria.list();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list(org.hibernate.criterion.DetachedCriteria)
	 */

	@Override
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> list(DetachedCriteria criteria) {

		return (List<T>) list(criteria.getExecutableCriteria(getSession()));

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list(java.lang.String, boolean)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(String orderBy, boolean isAsc) {

		Criteria criteria = createCriteria();

		if (isAsc) {

			criteria.addOrder(Order.asc(orderBy));

		} else {

			criteria.addOrder(Order.desc(orderBy));

		}

		return criteria.list();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list(java.lang.String, java.lang.Object)
	 */

	@Override
	public List<T> list(String propertyName, Object value) {

		Criterion criterion = Restrictions

		.like(propertyName, "%" + value + "%");

		return list(criterion);

	}

	/**
	 * 
	 * 根据查询条件获取数据列表
	 */

	@SuppressWarnings("unchecked")
	private List<T> list(Criterion criterion) {

		Criteria criteria = createCriteria();

		criteria.add(criterion);

		return criteria.list();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#list(org.hibernate.criterion.Criterion)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<T> list(Criterion... criterions) {

		return createCriteria(criterions).list();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#uniqueResult(java.lang.String, java.lang.Object)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public T uniqueResult(String propertyName, Object value) {

		Criterion criterion = Restrictions.eq(propertyName, value);

		return (T) createCriteria(criterion).uniqueResult();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#uniqueResult(org.hibernate.criterion.Criterion)
	 */

	@Override
	public T uniqueResult(Criterion... criterions) {

		Criteria criteria = createCriteria(criterions);

		return uniqueResult(criteria);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#uniqueResult(org.hibernate.Criteria)
	 */

	@Override
	@SuppressWarnings("unchecked")
	public T uniqueResult(Criteria criteria) {

		return (T) criteria.uniqueResult();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#distinct(org.hibernate.Criteria)
	 */

	// 认为没用

	@Override
	public Criteria distinct(Criteria criteria) {

		// 将结果集进行一次封装，封装成DISTINCT_ROOT_ENTITY对象，方便service层代码使用

		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return criteria;

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#flush()
	 */

	@Override
	public void flush() {

		getSession().flush();

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#clear()
	 */

	@Override
	public void clear() {

		getSession().clear();

	}

	/*
	 * 
	 * 创建Criteria实例
	 */

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#createCriteria()
	 */
	@Override
	public Criteria createCriteria() {

		return getSession().createCriteria(entityClass);

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#createCriteria(org.hibernate.criterion.Criterion)
	 */

	@Override
	public Criteria createCriteria(Criterion... criterions) {

		Criteria criteria = createCriteria();

		for (Criterion c : criterions) {

			criteria.add(c);

		}

		return criteria;

	}

	/* (non-Javadoc)
	 * @see com.player.core.dao.entitydao.base.IBaseDao#findPage(org.hibernate.Criteria, int, int)
	 */

	@Override
	public List<T> findPage(Criteria criteria, int pageNo, int pageSize) {

		// 设置起始结果数

		criteria.setFirstResult((pageNo - 1) * pageSize);

		// 返回的最大结果集

		criteria.setMaxResults(pageSize);

		return list(criteria);

	}
	

	@Override
	public List<T> find(QueryRule queryRule) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        QueryRuleUtils.createCriteriaWithQueryRule(criteria, queryRule);

        List<Order> orders = QueryRuleUtils.getOrderFromQueryRule(queryRule);
        for (Order o : orders) {
            criteria.addOrder(o);
        }
        return criteria.setFirstResult(0).list();
    }

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
	/*
	 * public Page<T> pagedQuery(Criteria criteria, int pageNo, int pageSize) {
	 * 
	 * Assert.isTrue(pageNo >= 1, "pageNO should start from 1");
	 * 
	 * // 返回查询结果集
	 * 
	 * List<T> list = findPage(criteria, pageNo, pageSize);
	 * 
	 * 
	 * 
	 * 
	 * 注：因为finaPage方法改变了查询条件导致countALL方法查询为空， 所以必须重新设置setFirstResult为0
	 * 
	 * 
	 * criteria.setFirstResult(0);
	 * 
	 * // count查询
	 * 
	 * // 获得查询总数
	 * 
	 * long totalCount = countAll(criteria);
	 * 
	 * if (totalCount < 1) {
	 * 
	 * return new Page<T>();
	 * 
	 * }
	 * 
	 * // 实际查询返回分页对象
	 * 
	 * int startIndex = Page.getStartOfPage(pageNo, pageSize);
	 * 
	 * return new Page<T>(startIndex, totalCount, pageSize, list);
	 * 
	 * }
	 * 
	 * 
	 * * 分页查询Criteria
	 * 
	 * 
	 * 
	 * @param
	 * 
	 * 
	 * 
	 * @return
	 * 
	 * 
	 * public Page<T> pagedQuery(ConditionQuery conditionQuery, OrderBy orderBy,
	 * 
	 * int pageNo, int pageSize) {
	 * 
	 * Assert.isTrue(pageNo >= 1, "pageNO should start from 1");
	 * 
	 * Criteria criteria = createCriteria();
	 * 
	 * // 构造查询条件和排序
	 * 
	 * conditionQuery.build(criteria);
	 * 
	 * orderBy.build(criteria);
	 * 
	 * // count查询
	 * 
	 * // 获得查询总数
	 * 
	 * long totalCount = countAll(criteria);
	 * 
	 * if (totalCount < 1) {
	 * 
	 * return new Page<T>();
	 * 
	 * }
	 * 
	 * // 实际查询返回分页对象
	 * 
	 * int startIndex = Page.getStartOfPage(pageNo, pageSize);
	 * 
	 * // 返回查询结果集
	 * 
	 * List<T> list = findPage(criteria, pageSize, pageNo);
	 * 
	 * return new Page<T>(startIndex, totalCount, pageSize, list);
	 * 
	 * }
	 */

}