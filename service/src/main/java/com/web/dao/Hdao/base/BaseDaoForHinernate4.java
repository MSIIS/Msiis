package com.web.dao.Hdao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.model.PageInfo;
import com.util.model.QueryRule;
import com.util.tools.BeanUtils;
import com.util.tools.QueryRuleUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;



@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseDaoForHinernate4<T extends Serializable, PK extends Serializable> implements  IBaseDao<T, PK>{

	// 泛型反射类
	protected Class<T> entityClass;
	
	
	// 通过反射获取子类确定的泛型类
	/**
	 *
	 * 注入sessionFactory
	 */

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDaoForHinernate4() {

		Type genType = getClass().getGenericSuperclass();

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		entityClass = (Class) params[0];

	}

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
	public List<T>
    find(QueryRule queryRule) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        QueryRuleUtils.createCriteriaWithQueryRule(criteria, queryRule);

        List<Order> orders = QueryRuleUtils.getOrderFromQueryRule(queryRule);
        for (Order o : orders) {
            criteria.addOrder(o);
        }
        return criteria.setFirstResult(0).list();
    }

    @Override
    public List<T> findByHql(String hql, Object[] values) {
        Assert.hasText(hql);
        String newHql = this.getNewHql(hql, values);
        Query query = this.getSession().createQuery(newHql);
        return this.setParameters(query, values).list();
    }

    @Override
    public int executeHql(String hql, Object[] values) {
        Assert.hasText(hql);
        String newHql = this.getNewHql(hql, values);
        Query query = this.getSession().createQuery(newHql);
        return this.setParameters(query,values).executeUpdate();
    }

    @Override
    public PageInfo<T> getPagenInfo(QueryRule queryRule, int pageNo, int pageSize) {
        return null;
    }


    private String getNewHql(String hql, Object[] values) {
        String newHql = hql;
        int pos = 0;
        if (values != null) {
            for (int i = 0; i < values.length; ++i) {
                pos = newHql.indexOf(63, pos);
                if (pos == -1) {
                    break;
                }
                if (pos > -1) {
                    newHql = newHql.substring(0, pos) + ":queryParam" + i + newHql.substring(pos + 1);
                }
                pos += 1;
            }
        }
        return newHql;
    }

    private Query setParameters(Query query, Object[] values) {
        if (values != null) {
            for (int i = 0; i < values.length; ++i) {
                if (values[i] instanceof Collection) {
                    query.setParameterList("queryParam" + i, (Collection<?>) values[i]);
                } else {
                    query.setParameter("queryParam" + i, values[i]);
                }
            }
        }
        return query;
    }

    /**
     * 这个分页的实现有个弊端，就是hibernate Criteria.uniqueResult()默认是返回了int,必须实体的ID也是int类型
     * @param queryRule
     * @param pageNo
     * @param pageSize
     * @return
     */
    public   PageInfo<T> findPageInfo(QueryRule queryRule , int pageNo, int pageSize){
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        PageInfo<T> pageInfo = new PageInfo<T>(pageNo, pageSize);
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        QueryRuleUtils.createCriteriaWithQueryRule(criteria, queryRule);
        CriteriaImpl impl = (CriteriaImpl) criteria;
        Projection projection = impl.getProjection();
        List orderEntries= null;
        try {
            orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
            BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");

        }
        int totalCount = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        pageInfo.setTotalCount(totalCount);
        if (pageNo > pageInfo.getTotalPageCount()) {
            pageNo = pageInfo.getTotalPageCount();
            pageInfo.setPageNo(pageNo);
        }
        if (totalCount < 1) {
            return pageInfo;
        }
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        try {
            BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }

        List<Order> orders = QueryRuleUtils.getOrderFromQueryRule(queryRule);
        for (Order o : orders) {
            criteria.addOrder(o);
        }
        int startIndex = PageInfo.getStartIndex(pageNo, pageSize);
        List<T> data = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        pageInfo.setData(data);
        return  pageInfo;
    }



    @Override
    public PageInfo<T>  findPageInfoBySql(String hql ,int pageNo ,int pageSize ,Object[] values,Object[] countParams ,String countHql){
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        PageInfo<T> pageInfo = new PageInfo<T>(pageNo, pageSize);
        String newHql = this.getNewHql(hql, values);

        int totalCount = 0;
        if (countHql == null || countHql.length() == 0) {
            countHql =new StringBuffer(newHql.length() + 20).append(" select count(*) ").append(this.removeSelect(this.removeOrders(newHql))).toString();
            countParams = values;
        } else {
            countHql = this.getNewHql(countHql, values);
        }

        Query countQuery = this.getSession().createQuery(this.getNewHql(countHql, countParams));
        List<?> countlist = this.setParameters(countQuery, countParams).list();
        if (countlist != null && countlist.size() > 0) {
            totalCount = Integer.parseInt(countlist.get(0) + "");
        }
        if (countlist != null && countlist.size() > 0) {
            totalCount = Integer.parseInt(countlist.get(0) + "");
        }
        pageInfo.setTotalCount(totalCount);
        if (pageNo > pageInfo.getTotalPageCount()) {
            pageNo = pageInfo.getTotalPageCount();
            pageInfo.setPageNo(pageNo);
        }
        if (totalCount < 1) {
            return pageInfo;
        }
        Query dataQuery = this.getSession().createQuery(newHql);
        List<T> data = this.setParameters(dataQuery, values).setFirstResult(PageInfo.getStartIndex(pageNo, pageSize)).setMaxResults(pageSize).list();
        pageInfo.setData(data);

        return  pageInfo;
    }


    /**
     * 执行原生sql
     *
     * @param baseWork work子类
     */
    public void doWork(BaseWork baseWork) {
        this.getSession().doWork(baseWork);
    }

    private String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase(Locale.US).indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    private String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}