package com.web.dao.Hdao;


import com.util.model.QueryRule;
import com.web.dao.Hdao.base.BaseWork;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.roll.Roll;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unchecked")
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
                 ex.printStackTrace();

             }
         }
     });
    }

    public  List<Roll> findObjectByJdbc(Long id){
       final   String sql = "select id,blue,red,red_sum,numberField from m_roll where  id =? ";
       return  (List<Roll>)this.getJdbcTemplate().query(sql,new Object[]{id},new BeanPropertyRowMapper(Roll.class));
    }

}
