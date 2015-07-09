package com.web.dao.Hdao.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.model.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 基础DAO
 *
 * @param <T>
 * @param <PK>
 */
public abstract class HBaseDao<T extends Serializable, PK extends Serializable> extends BaseDaoForHinernate4<T, PK> {
    // 日志输出类
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoForHinernate4.class);

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询
     */
    public List<T> queryForList(String sql) {
        return this.jdbcTemplate.queryForList(sql, this.entityClass);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * spring jdbc sql 方式抽取分页数据
     *
     * @param sql
     * @return
     */
    public PageInfo<T> findPageInfoByJdbcSql(String sql, String countSql) {
        return null;
    }

    public List<T> findObjectByJdbcSql(String sql) {
        List<T> list = jdbcTemplate.queryForList(sql, this.entityClass);
        return list;
    }
    public void executeJdbcSql(String sql){
        jdbcTemplate.execute(sql);
    }

}
