package com.web.dao.Hdao;


import com.util.model.PageInfo;
import com.util.model.QueryRule;
import com.web.dao.Hdao.base.BaseWork;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.Roll;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component("rollDaoH4")
public class RollDaoH4 extends HBaseDao<Roll, Long> {
	
 public List<Roll> findByRule(QueryRule rule){
	return  this.find(rule);
 }

    public void insertBatch(final List<Roll> list) {
    final String sql = "insert into user (id ,rend ,blue) values (?,?,?)";
     this.doWork(new BaseWork() {
         @Override
         public void execute(Connection connection) throws SQLException {
             PreparedStatement ps = connection.prepareStatement(sql);
             try{
                 for (Roll roll :list){
                     ps.setLong(1,roll.getId());
                     ps.setString(2,roll.getRed());
                     ps.setString(3,roll.getBlue());
                     ps.addBatch();
                 }
                 ps.executeBatch();
             }catch (SQLException ex){

             }
         }
     });
    }

    public  List<Roll> findObjectByJdbc(Long id){
       final   String sql = "select * from roll where  id =? ";
       return this.getJdbcTemplate().queryForList(sql,new Object[]{id},Roll.class);
    }

}
