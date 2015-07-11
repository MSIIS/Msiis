package com.web.dao.Hdao;

import com.web.dao.Hdao.base.BaseWork;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Component("userDaoH4")
public class UserDaoH4 extends HBaseDao<User, Long> {

    public void insertBatchH(final List<User> collection) {
            final String sql = "insert into soupe_user (user_id ," +
                    " user_name ,password,status ,is_deleted,create_time,nick_name,real_name) values (?,?,?,?,?,?,?,?)";
            this.doWork(new BaseWork() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    Timestamp timestamp =  new Timestamp(new Date().getTime());

                    try{
                        for (User user : collection){
                            ps.setLong(1,user.getId());
                            ps.setString(2, user.getUserName());
                            ps.setString(3,user.getPassword());
                            ps.setInt(4, user.getStatus());
                            ps.setBoolean(5, user.isDeleted());
                            ps.setTimestamp(6, timestamp);
                            ps.setString(7,user.getNickName());
                            ps.setString(8,user.getRealName());
                            ps.addBatch();
                        }
                      ps.executeBatch();
                    }catch (SQLException ex){
                       ex.printStackTrace();
                    }
                }
            });

    }
}