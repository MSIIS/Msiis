package com.web.dao.Hdao;


import com.util.model.QueryRule;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.Roll;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rollDaoH4")
public class RollDaoH4 extends HBaseDao<Roll, Long> {
	
 public List<Roll> findByRule(QueryRule rule){
	return  this.find(rule);
 }

}
