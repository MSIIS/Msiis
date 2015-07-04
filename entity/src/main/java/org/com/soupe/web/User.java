package org.com.soupe.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@Table(name = "soupe_user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id",nullable =false)
    private  Long id ;
    @Column(name = "user_name",nullable = false)
    private  String userName;
    @Column(name = "passsord",nullable = false)
    private  String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
